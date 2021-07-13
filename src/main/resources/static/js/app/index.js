var projectId = $("#projectId").text() || "";

var main = {
    init : function() {
        var _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
        $('#makeProductName').on('click', function() {
            _this.makeProductName();
        });
        $('#viewDemo').on('click', function() {
            _this.viewDemo();
        });



    },
    save : function() {
        var data = {
            productType: $('#productType').val(),
            productName: $('#productName').val(),
            productOrder: $('#productOrder').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/productKindSave',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('상품타입이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        })
    },
    makeProductName : function() {
        var productType = $("#productType option:selected").val();
        var productName = $("#productName").val();
        var brightnessList = $("#brightness").val();
        $('#productNameTBody').empty();

        $.each (brightnessList, function (index, el) {
            var tmpProductName = '';
            console.log('element', index, el);
            if (productName.indexOf('$호수') > -1) {
                tmpProductName = productName.replace(/\$호수/gi, el);
            }

            var trObj = $('<tr>').addClass('table-primary');
            var thObj = $('<th>').attr('scope', 'row');
            var tdObj = $('<td>').text(tmpProductName);
            trObj.append(thObj).append(tdObj);
            $('#productNameTBody').append(trObj);

            console.log('tmpProductName : ', tmpProductName);
        });
    },
    viewDemo : function() {
        var productName = $("#productName").val();
        var brightnessList = $("#brightness").val();

        $.each (brightnessList, function (index, el) {
            var tmpProductName = '';
            if (productName.indexOf('$호수') > -1) {
                tmpProductName = productName.replace(/\$호수/gi, el);
            }

            var circleObj = $('<div>').addClass('circle');
            $('#viewDemoDiv').append(circleObj);

            console.log('tmpProductName : ', tmpProductName);
        });

    }
};

var projectList = {
    init : function() {
        var _this = this;
        $('.a-view').on('click', function() {
            _this.goDetail(this);
        });
    },
    goDetail : function(obj) {
        var projectId = $(obj).closest('tr').children(':first-child').text().replace(/[^0-9]/g, "");
        location.href = "/projectDetail?projectId="+projectId;
    }
};


var marketingDraft = {
    init : function() {
        var _this = this;
        $('#createTemplate').on('click', function() {
            _this.createTemplate(this);
        });
        $('#startDraft').on('click', function() {
            _this.showDraftContents(this);
        });
        $('#saveDraft').on('click', function() {
            _this.saveDraft(this);
        });
        $('#goToDetail').on('click', function() {
            _this.goToDetail(this);
        });






    },
    createTemplate : function(obj) {
        $('.showTemplate').show();
    },
    showDraftContents : function(obj) {
        $('.draftContents').show();
    },
    saveDraft : function(obj) {

    },
    goToDetail : function(obj) {
        location.href = "/projectDetail?projectId="+projectId;
    }




};


main.init();

<!-- Project List -->
projectList.init();

<!-- Draft -->
marketingDraft.init();




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

//
//        $('#viewDemoDiv').append();
//        <div id="circle" style="width: 500px;
//          height: 500px;
//         border: 1px solid;
//          border-radius: 50%"></div>
    }
};

main.init();
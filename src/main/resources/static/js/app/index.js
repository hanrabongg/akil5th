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

var projectList = {
    init : function() {
        var _this = this;
        $('.a-view').on('click', function() {
            _this.goDetail(this);
        });
    },
    goDetail : function(obj) {
        var projectID = $(obj).closest('tr').children(':first-child').text().replace(/[^0-9]/g, "");
        location.href = "/projectDetail?id="+projectID;
    }
};

var projectDetail = {
    init : function() {
        var _this = this;
        $('.viewImg').on('click', function() {
            _this.viewImg(this);
        });
        $('.a-mak-new').on('click', function() {
            _this.goMakNew(this);
        });
        $('.a-mak-edit').on('click', function() {
            _this.goMakEdit(this);
        });

        $('.imgDetect').on('click', function() {
            _this.detectFile(this, "API");
        });

        $('.pdfDetect').on('click', function() {
            _this.detectFile(this, "TIKA");
        });




    },
    viewImg : function(obj) {
        event.preventDefault();
        $(obj).ekkoLightbox({
            alwaysShowClose: true
        });
    },

    goMakNew : function(obj) {
        var projectID = $("#projectId").text();
        location.href = "/marketingDraft?projectId="+projectID+"&requestType=NEW";
    },
    goMakEdit : function(obj) {
        var projectID = $("#projectId").text();
        location.href = "/marketingDraft?projectId="+projectID+"&requestType=EDIT";
    },
    detectFile : function(obj, flag) {
        event.preventDefault();
        var projectId = $("#projectId").text();
        var url = (flag == "API") ? "/api/v1/getTextViaAPI" : "/api/v1/getTextViaTika";

        var sendData = {"projectId":projectId, "fileUrl": "/dist/file/1.루나에센스수분광팩트CX_디자인.jpg"}

        $.ajax({
            url: url,
            type: "POST",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(sendData),
            dataType: 'json',
            success: function(response) {
                showDesignText(response);
            },
            error: function(xhr, status, msg) {
                console.log("상태값 : "+ status + "Http에러메세지" + msg);
            }

        });
    }




};

function showDesignText(obj) {
    $('#designText').text(JSON.stringify(obj.detectedText))
    console.log("string : " + JSON.stringify(obj));
}

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
        var projectID = $("#projectId").text();
        location.href = "/projectDetail?id="+projectID;
    }




};


main.init();

<!-- Project List -->
projectList.init();

<!-- Project Detail -->
projectDetail.init();

<!-- Draft -->
marketingDraft.init();




// DropzoneJS Demo Code Start
Dropzone.autoDiscover = false

// Get the template HTML and remove it from the doumenthe template HTML and remove it from the doument
var previewNode = document.querySelector("#template")
previewNode.id = ""
var previewTemplate = previewNode.parentNode.innerHTML
previewNode.parentNode.removeChild(previewNode)

var myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
    url: "/target-url", // Set the url
    thumbnailWidth: 80,
    thumbnailHeight: 80,
    parallelUploads: 20,
    previewTemplate: previewTemplate,
    autoQueue: false, // Make sure the files aren't queued until manually added
    previewsContainer: "#previews", // Define the container to display the previews
    clickable: ".fileinput-button" // Define the element that should be used as click trigger to select files.
})

myDropzone.on("addedfile", function(file) {
    // Hookup the start button
    file.previewElement.querySelector(".start").onclick = function() { myDropzone.enqueueFile(file) }
})

// Update the total progress bar
myDropzone.on("totaluploadprogress", function(progress) {
    document.querySelector("#total-progress .progress-bar").style.width = progress + "%"
})

myDropzone.on("sending", function(file) {
    // Show the total progress bar when upload starts
    document.querySelector("#total-progress").style.opacity = "1"
    // And disable the start button
    file.previewElement.querySelector(".start").setAttribute("disabled", "disabled")
})

// Hide the total progress bar when nothing's uploading anymore
myDropzone.on("queuecomplete", function(progress) {
    document.querySelector("#total-progress").style.opacity = "0"
})

// Setup the buttons for all transfers
// The "add files" button doesn't need to be setup because the config
// `clickable` has already been specified.
document.querySelector("#actions .start").onclick = function() {
    myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED))
}
document.querySelector("#actions .cancel").onclick = function() {
    myDropzone.removeAllFiles(true)
}
// DropzoneJS Demo Code End


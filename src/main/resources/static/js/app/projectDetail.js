
var projectId = $("#projectId").text();

var projectDetail = {
    init : function() {
        var _this = this;
        $('.viewImg').on('click', function() {
            //_this.viewImg(this);
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

        $('.fileRefresh').on('click', function() {
            window.location.reload();
        });


    },
    goMakNew : function(obj) {
        location.href = "/marketingDraft?projectId="+projectId+"&requestType=NEW";
    },
    goMakEdit : function(obj) {
        location.href = "/marketingDraft?projectId="+projectId+"&requestType=EDIT";
    },
    detectFile : function(obj, flag) {
        $("#designText").empty();
        $('.designLoading').show();
        event.preventDefault();
        var url = (flag == "API") ? "/api/v1/getTextViaAPI" : "/api/v1/getTextViaTika";

        var sendData = {"projectId":projectId, "fileUrl": "/dist/file/1.루나에센스수분광팩트CX_디자인.jpg"}

        $.ajax({
            url: url,
            type: "POST",
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(sendData),
            dataType: 'json',
            success: function(response) {
                $('.designLoading').hide();
                showDesignText(response);
            },
            error: function(xhr, status, msg) {
                console.log("상태값 : "+ status + "Http에러메세지" + msg);
            }

        });
    }
};

function showDesignText(obj) {
    var resultText = JSON.stringify(obj.detectedText);
    resultText = resultText.replace('\"', "").replace(/"\s*$/, ""); // 앞뒤 "" 삭제
    resultText = resultText.replace(/\\n/g, '<br>'); // 개행문자 줄바꿈 처리

    $('#designText').html(resultText);
}

<!-- Project Detail -->
projectDetail.init();




// DropzoneJS Demo Code Start
Dropzone.autoDiscover = false

// Get the template HTML and remove it from the doumenthe template HTML and remove it from the doument
var previewNode = document.querySelector("#designUploader")
previewNode.id = ""
var previewTemplate = previewNode.parentNode.innerHTML
previewNode.parentNode.removeChild(previewNode)

var myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
    url: "/api/v1/uploadFiles?projectId="+projectId, // Set the url
    method: "post",
    thumbnailWidth: 80,
    thumbnailHeight: 80,
    parallelUploads: 20,
    previewTemplate: previewTemplate,
    uploadMultiple: true,
    acceptedFiles: "image/*,application/pdf,.doc,.docx,.xls,.xlsx,.csv,.tsv,.ppt,.pptx,.pages,.odt,.rtf",
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
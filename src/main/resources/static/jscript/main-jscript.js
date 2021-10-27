$(document).ready(function () {
    $('#fileImage').change(function () {
        showImage(this);
    });
});

function showImage(fileInput) {
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function (e) {
        $('#image-brand').attr('src',e.target.result);
    }
    reader.readAsDataURL(file);
    $('#fileImage')[0].setAttribute("value",file.name);
    console.log(file);
}
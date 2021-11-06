$(document).ready(function() {
    $('#change-password-form')[0].addEventListener('submit', (e) => {
        if (checkInput() === true) {
            console.log("true")
            $('#change-password-form')[0].action = "/admin/staff/change-password/save";
        } else {
            e.preventDefault();
        }
    });
    $("#show-new-password")[0].addEventListener('click', (e) => {
        if ($('#new-password')[0].type === "password") {
            $('#new-password')[0].type = "text";
            $("#show-new-password")[0].classList.replace('fa-eye', 'fa-eye-slash');
        } else {
            $('#new-password')[0].type = "password";
            $("#show-new-password")[0].classList.replace('fa-eye-slash', 'fa-eye');
        }
    });
    $("#show-old-password")[0].addEventListener('click', (e) => {
        if ($('#old-password')[0].type === "password") {
            $('#old-password')[0].type = "text";
            $("#show-old-password")[0].classList.replace('fa-eye', 'fa-eye-slash');
        } else {
            $('#old-password')[0].type = "password";
            $("#show-old-password")[0].classList.replace('fa-eye-slash', 'fa-eye');
        }
    });
    $("#show-verify-password")[0].addEventListener('click', (e) => {
        if ($('#verify-password')[0].type === "password") {
            $('#verify-password')[0].type = "text";
            $("#show-verify-password")[0].classList.replace('fa-eye', 'fa-eye-slash');
        } else {
            $('#verify-password')[0].type = "password";
            $("#show-verify-password")[0].classList.replace('fa-eye-slash', 'fa-eye');
        }
    });
});
function checkInput() {
    let isCheck = true;
    const oldPassword = $('#old-password')[0].value;
    const newPassword = $('#new-password')[0].value;
    const verifyPassword = $('#verify-password')[0].value;

    if (oldPassword === '') {
        setErrorFor($('#old-password')[0], "Old password cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#old-password')[0]);
    }
    if (newPassword === '') {
        setErrorFor($('#new-password')[0], "New password cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#new-password')[0]);
    }
    if (verifyPassword === '') {
        setErrorFor($('#verify-password')[0], "Verify password cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#verify-password')[0]);
    }
    if(verifyPassword!=newPassword){
        setErrorFor($('#verify-password')[0], "Verify password difference new password");
        isCheck = false;
    }else {
        setSuccessFor($('#verify-password')[0]);
    }
    return isCheck;
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    small.innerText = message;
    formControl.className = 'password-container error';
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'password-container success';
}
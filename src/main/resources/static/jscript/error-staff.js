$(document).ready(function() {
    $('#form-upload-staff')[0].addEventListener('submit', (e) => {
        if (checkInput() === true) {
            console.log("true")
            $('#form-upload-staff')[0].action = "/admin/staff/upload-staff/save";
        } else {
            e.preventDefault();
        }

    });
    $("#identity-card-staff").keypress(function(e) {
        var charCode = (e.which) ? e.which : e.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
    });

    $("#salary-staff").keypress(function(e) {
        var charCode = (e.which) ? e.which : e.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
    });

    $("#showpassword")[0].addEventListener('click', (e) => {
        if ($('#password-staff')[0].type === "password") {
            $('#password-staff')[0].type = "text";
            $("#showpassword")[0].classList.replace('fa-eye', 'fa-eye-slash');
        } else {
            $('#password-staff')[0].type = "password";
            $("#showpassword")[0].classList.replace('fa-eye-slash', 'fa-eye');
        }
    });
});

function checkInput() {
    let isCheck = true;
    const firstNameValue = $('#first-name-staff')[0].value.trim();
    const lastNameValue = $('#last-name-staff')[0].value.trim();
    const identityCardValue = $('#identity-card-staff')[0].value.trim();
    // const genderValue = $('#gender-staff')[0].value;
    const emailValue = $('#email-staff')[0].value;
    const phoneNumberValue = $('#phone-num-staff')[0].value;
    const dateOfBirthValue = $('#date-of-birth-staff')[0].value;
    const addressValue = $('#address-staff')[0].value.trim();
    const salaryValue = $('#salary-staff')[0].value;
    const usernameValue = $('#username-staff')[0].value.trim();
    const passwordValue = $('#password-staff')[0].value.trim();

    if (firstNameValue === '') {
        setErrorFor($('#first-name-staff')[0], "First name cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#first-name-staff')[0]);
    }
    if (lastNameValue === '') {
        setErrorFor($('#last-name-staff')[0], "Last name cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#last-name-staff')[0]);
    }

    if (identityCardValue === '') {
        setErrorFor($('#identity-card-staff')[0], "Identity card cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#identity-card-staff')[0]);
    }

    if (emailValue === '') {
        setErrorFor($('#email-staff')[0], "Email cannot blank");
        isCheck = false;
    } else {
        if (emailValue.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
            setSuccesFor($('#email-staff')[0]);
        } else {
            setErrorFor($('#email-staff')[0], "Email example: abc1@gmail.com");
            isCheck = false;
        }
    }

    if (phoneNumberValue === '') {
        setErrorFor($('#phone-num-staff')[0], "Phone number cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#phone-num-staff')[0]);
    }

    if (dateOfBirthValue === '') {
        setErrorFor($('#date-of-birth-staff')[0], "Date of birth cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#date-of-birth-staff')[0]);
    }

    if (addressValue === '') {
        setErrorFor($('#address-staff')[0], "Address cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#address-staff')[0]);
    }

    if (salaryValue === '') {
        setErrorFor($('#salary-staff')[0], "Salary cannot blank");
        isCheck = false;
    } else {
        setSuccesFor($('#salary-staff')[0]);
    }

    if (usernameValue === '') {
        setErrorFor($('#username-staff')[0], "Username cannot blank");
        isCheck = false;
    } else {
        if (usernameValue.match("^[a-zA-Z0-9]+$")) {
            setSuccessFor($('#username-staff')[0]);
        } else {
            setErrorFor($('#username-staff')[0], "Username only character or number.");
            isCheck = false;
        }
    }

    if (passwordValue === '') {
        setErrorFor($('#password-staff')[0], "Password cannot blank");
    } else {
        if (passwordValue.match("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})")) {
            setSuccessFor($('#password-staff')[0]);
        } else {
            setErrorFor($('#password-staff')[0], "User more than 8 characters and combinations of letters, numbers, and symbols. ");
            isCheck = false;
        }
    }
    return isCheck;
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    small.innerText = message;
    formControl.className = 'staff-container error';
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'staff-container success';
}
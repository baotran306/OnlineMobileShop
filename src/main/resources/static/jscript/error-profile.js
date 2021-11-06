$(document).ready(function() {
    $('#form-profile-staff')[0].addEventListener('submit', (e) => {
        var cf = confirm("Are you sure update profile?");
        if(cf===true) {
            if (checkInput() === true) {
                console.log("true")
                $('#form-profile-staff').action = "/admin/staff/profile/save";
            } else {
                e.preventDefault();
            }
        }else {
            e.preventDefault();
        }

    });
    $("#identity-card-info").keypress(function(e) {
        var charCode = (e.which) ? e.which : e.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
    });

    $("#phone-number-info").keypress(function(e) {
        var charCode = (e.which) ? e.which : e.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
    });

});


function checkInput() {
    let isCheck = true;
    const firstNameValue = $('#first-name-info')[0].value.trim();
    const lastNameValue = $('#last-name-info')[0].value.trim();
    const identityCardValue = $('#identity-card-info')[0].value.trim();
    const emailValue = $('#email-info')[0].value;
    const phoneNumberValue = $('#phone-number-info')[0].value;
    const dateOfBirthValue = $('#date-of-birth-info')[0].value;
    const addressValue = $('#address-info')[0].value.trim();

    if (firstNameValue === '') {
        setErrorFor($('#first-name-info')[0], "First name cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#first-name-info')[0]);
    }
    if (lastNameValue === '') {
        setErrorFor($('#last-name-info')[0], "Last name cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#last-name-info')[0]);
    }

    if (identityCardValue === '') {
        setErrorFor($('#identity-card-info')[0], "Identity card cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#identity-card-info')[0]);
    }

    if (emailValue === '') {
        setErrorFor($('#email-info')[0], "Email cannot blank");
        isCheck = false;
    } else {
        if (emailValue.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
            setSuccesFor($('#email-info')[0]);
        } else {
            setErrorFor($('#email-info')[0], "Email example: abc1@gmail.com");
            isCheck = false;
        }
    }

    if (phoneNumberValue === '') {
        setErrorFor($('#phone-number-info')[0], "Phone number cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#phone-number-info')[0]);
    }

    if (dateOfBirthValue === '') {
        setErrorFor($('#date-of-birth-info')[0], "Date of birth cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#date-of-birth-info')[0]);
    }

    if (addressValue === '') {
        setErrorFor($('#address-info')[0], "Address cannot blank");
        isCheck = false;
    } else {
        setSuccessFor($('#address-info')[0]);
    }

    return isCheck;
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    small.innerText = message;
    formControl.className = 'staff-container-info error';
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'staff-container-info success';
}
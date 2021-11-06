$(document).ready(function() {
    $('#form-update-staff')[0].addEventListener('submit', (e) => {
        if (checkInputUpdate() === true) {
            console.log("true")
            $('#form-update-staff')[0].action = "/admin/staff/update/save";
        } else {
            e.preventDefault();
        }

    });

    $("#salary-staff-update").keypress(function(e) {
        var charCode = (e.which) ? e.which : e.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
    });
});

function checkInputUpdate() {
    const salaryValue = $('#salary-staff-update')[0].value;
    if (salaryValue === '') {
        setErrorFor($('#salary-staff-update')[0], "Salary cannot blank");
        return false;
    } else {
        setSuccessFor($('#salary-staff-update')[0]);
        return true;
    }

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
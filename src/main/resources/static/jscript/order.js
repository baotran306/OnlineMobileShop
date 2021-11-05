$(document).ready(function() {
    $('#form-search-by-date')[0].addEventListener('submit', (e) => {
        if (checkInput() === true) {
            console.log("true")
            $('#form-search-by-date')[0].action = "/admin/order/search";
        } else {
            e.preventDefault();
        }

    })
});

function checkInput() {
    var isCheck=true;
    const fromDateValue = $('#from-date')[0].value;
    const toDate = $('#to-date')[0].value;
    if (fromDateValue === '') {
        setErrorFor($('#from-date')[0], "From date cannot blank");
        isCheck=false;
    } else {
        setSuccesFor($('#from-date')[0]);
    }

    if (toDate === '') {
        setErrorFor($('#to-date')[0], "To date cannot blank");
        isCheck = false;
    } else {
        setSuccesFor($('#to-date')[0]);
    }
    return isCheck;
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    small.innerText = message;
    formControl.className = 'order-container-date error';
}

function setSuccesFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'order-container-date success';
}
$(document).ready(function() {
    console.log("da vao")
    $('#form-upload-phone').submit((e) => {
        if (checkInput() === true) {
            $('#form-upload-phone').action = "/admin/phone/save";
        } else {
            e.preventDefault();
        }

    })
});

function checkInput() {
    console.log("true");
    const phoneNameValue = $('#phoneName')[0].value.trim();
    const brandValue = $('#brand-phone')[0].value;
    const colorValue = $('#color-phone')[0].value;
    const priceValue = $('#price')[0].value;
    const quantityValue = $('#quantity')[0].value;
    const descriptionValue = $('#description')[0].value.trim();
    const fileValue = $('#fileImage')[0].getAttribute("value");
    if (phoneNameValue === '') {
        setErrorFor($('#phoneName')[0], "Phone name cannot blank");
    } else {
        setSuccesFor($('#phoneName')[0]);
    }

    if (brandValue === '') {
        setErrorFor($('#brand-phone')[0], "Brand cannot blank");
    } else {
        setSuccesFor($('#brand-phone')[0]);
    }

    if (colorValue === '') {
        setErrorFor($('#color-phone')[0], "Color cannot blank");
    } else {
        setSuccesFor($('#color-phone')[0]);
    }

    if (priceValue === '') {
        setErrorFor($('#price')[0], "Price cannot blank");
    } else {
        setSuccesFor($('#price')[0]);
    }

    if (quantityValue === '') {
        setErrorFor($('#quantity')[0], "File cannot blank");
    } else {
        setSuccesFor($('#quantity')[0]);
    }

    if (descriptionValue === '') {
        setErrorFor($('#description')[0], "Description cannot blank");
    } else {
        setSuccesFor($('#description')[0]);
    }

    if (fileValue === '') {
        const formControl = $('#fileImage')[0].parentElement;
        const small = formControl.querySelector('small');
        message = "Image cannot blank";
        small.innerText = message;
        formControl.className = 'container-input-file error';
    } else {
        const formControl = $('#fileImage')[0].parentElement;
        formControl.className = 'container-input-file succes';
    }
    if (fileValue !== '' && quantityValue !== '' && priceValue !== '' &&
        descriptionValue !== '' && brandValue!=='' && colorValue!=='' && phoneNameValue!=='') {
        return true;
    } else {
        return false;
    }
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    small.innerText = message;
    formControl.className = 'phone-container error';
}

function setSuccesFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'phone-container success';
}
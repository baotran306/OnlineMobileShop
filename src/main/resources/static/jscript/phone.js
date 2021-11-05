
    $(document).ready(function () {
        /*<![CDATA[*/
        var phones = /*[[${phones}]]*/ 'default';
        alert(phones);
        /*]]>*/
    $('#text-search-phone').on('keyup',function () {
        var value = $(this).val();
        var data = FilterFunction(value,phones);
        rebuildTable(data);
    });
});

function FilterFunction(value, data) {
    var filterData=[];
    for (var i=0;i<data.length;i++){
        value = value.toLowerCase();
        var pname = data[i].phoneName.toLowerCase();
        if(pname.includes(value)){
            filterData.push(data[i]);
        }
    }
    return filterData;
}

function rebuildTable(data) {
    var table = $('#table-phone');
    table.innerHTML='';
    for (var i=0;i<data.length;i++){
        var row = `<tr >
                <td />
                 <td class="td-phone-name" th:text="${data[i].phoneName}"/>
                <td class="td-phone-image">
                <img th:src="'http://127.0.0.1:5000/get-image/'+${data[i].image}" th:width="200px" th:height="150px"/>
                </td>
                <td class="td-phone-brand" th:text="${data[i].brand}"/>
                <td class="td-phone-color" th:text="${data[i].color}"/>
                <td class="td-phone-price">
                <span th:text="${data[i].price}">
                </span>VNĐ</td>
                <td class="td-phone-quantity" th:text="${data[i].quantity}"/>
                <td class="td-phone-description" th:text="${data[i].description}"/>
                <td class="td-phone-action">
                <a class="btn btn-primary btn-sm mb-3" th:href="@{/admin/updatePhone(idPhone=${data[i].id})}">Update</a>
                </td>
            </tr>`
        table.innerHTML +=row;

    }

}
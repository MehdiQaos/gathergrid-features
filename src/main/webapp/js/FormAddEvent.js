let options = [];
$("#type_ticket option").each(function(index,value){
    let optionObject = {
        value : index,
        isSelected : false,
        typeTicket : $(value).text()
    }
    options.push(optionObject);
})

$(document).ready(function() {
    $(document).on("click",".add_item_btn",function(e) {
        if($("#show_item")[0].childElementCount < 4){
            appendFormTicket();
            let parentTicketContainer = $(this).parent().parent().parent();
            let inputs = parentTicketContainer.find("input").prop("disabled", true)
            let selctedValue= parentTicketContainer.find("select")
            isSelectedOption(selctedValue.val(), true)
            selctedValue.prop("disabled", true)
            for(const option of options){
                if(option.isSelected == false){
                    $(".select-type-ticket").prepend(`<option value="${option.value}"> ${option.typeTicket} </option>`)
                }
            }
            $(this).parent().html(`
                    <button type="button"
                        class="mt-10 p-2 bg-cl2 relative border rounded-md 
                        hover:bg-hover2 font-bold text-sm remove_item_btn ">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                `)
        }else{
            e.preventDefault()
        }
    });

    function isSelectedOption(param, removed){
        for(const option of options){
            if(option.value == param && removed == true){
                option.isSelected = true;
            }else if(option.value == param && removed == false){
                option.isSelected = false;
            }
        }
    }


    $(document).on("click", ".remove_item_btn", function(e) {
        e.preventDefault()
        let selctedValue= $(this).parent().parent().parent().find("select option:selected");
        isSelectedOption(selctedValue.val(), false)
        console.log($(this).parent().parent().parent());
        let row_item = $(this).parent().parent().parent();
        $(row_item).remove();
        $("#show_item .select-type-ticket:first").prepend(`<option value="${selctedValue.val()}"> ${selctedValue.text()} </option>`)
    });
    function appendFormTicket() {
        let newFormTickets = $(`
                    <div class="p-4 flex relative justify-between items-center mx-auto px-8 shadow-md pb-2 pt-2 append_item">
                        <div class="mx-1">
                          <label for="quantity" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Number of ticket available</label>
                          <input type="number" name="quantity[]" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
                        </div>
                        <div class="mx-1">
                          <label for="type" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Ticket Type</label>
                          <select name="type[]" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 select-type-ticket">
                          </select>
                        </div>
                        <div class="mx-1">
                          <label for="price" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Price</label>
                          <input type="number" name="price[]" step="0.01" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
                        </div>
                        <div class="flex relative justify-between items-center mx-auto" >
                            <div class="pl-5 pb-3">
                            <button type="button" class="mt-10 p-2 bg-cl2 relative border rounded-md hover:bg-hover2 font-bold text-sm add_item_btn">
                                <i class="fa-solid fa-plus"></i>                                    
                            </button>
                            </div>
                        </div>
                    </div>
                    `)
        $("#show_item").prepend(newFormTickets)
    }
    function toggleSideBar() {
        $("#sideBar").toggleClass('active')
    }

    function toggleSideBar() {
        $("#sideBar").toggleClass('active')
    }
    let optional_config = {
        enableTime: true,
        dateFormat: "Y-m-d H:i",
    }
    $("#dateEvent").flatpickr(optional_config);

})


$(document).on("submit","#containerForm-add", function(e){e.preventDefault()
    $("#containerForm-add").find(":input").each(function(){
        console.log($(this).val());
        $(this).prop("disabled",false);
    })
})
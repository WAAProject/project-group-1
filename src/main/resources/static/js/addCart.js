$(function() {
    $("[name = 'addToCart']").on("click", addToCart);
});

function addToCart() {
    // alert("inside");
    let productId = $(this).attr("value");

    $.ajax("http://localhost:8080/cart",{
        method:"POST",
        data: JSON.stringify({"productId":productId,
            "quantity":1}),
        dataType: "json",
        contentType: "application/json"

    }).done(function(resp) {

        $("#exampleModalLabel").html(resp.message);
        // $("#exampleModalBody").html(resp.message);
        $('#exampleModal').modal("show");
        $("#headerCartSize").html(resp.size);
        // alert(resp.message + ". Total items: " + resp.size);
        $("[name='cartItems']").html(resp.size)

    }).fail(function (resp) {

        alert("failed " + resp.message);
    })
}
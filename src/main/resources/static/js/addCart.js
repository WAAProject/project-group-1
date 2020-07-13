$(function() {
    $("#addToCart").on("click", addToCart);
});

function addToCart() {
    alert("inside");
    let productId = $(this).attr("value");

    $.ajax("http://localhost:8080/cart",{
        method:"POST",
        data: JSON.stringify({"productId":productId,
            "quantity":1}),
        dataType: "json",
        contentType: "application/json"

    }).done(function(resp) {

        alert(resp.message + ". Total items: " + resp.size);

    }).fail(function (resp) {

        alert("failed " + resp.message);
    })
}
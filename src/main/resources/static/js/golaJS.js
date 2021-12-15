document.addEventListener("DOMContentLoaded", function() {
    const callBackFunc = function(response) {
        alert(response)
    };
    document.getElementById("milestone").textContent = ' > 주문';
    //callAPI('GET', 'http://localhost/comments', '', callBackFunc);
});
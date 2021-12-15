document.addEventListener("DOMContentLoaded", function() {
    const callBackFunc = function(response) {
        alert(response)
    };
    setMilestonePath(' > 주문');
    //callAPI('GET', 'http://localhost/comments', '', callBackFunc);
});
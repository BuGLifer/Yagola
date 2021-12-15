document.addEventListener("DOMContentLoaded", function() {
    const callBackFunc = function(response) {
        alert(response)
    };
    setMilestonePath(' > 모집');
    const queryString = getQueryString();
    console.log(queryString);
    console.log(queryString.tt);

    //callAPI('GET', 'http://localhost/comments', '', callBackFunc);
});
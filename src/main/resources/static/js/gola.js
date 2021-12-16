document.addEventListener("DOMContentLoaded", function() {
    setMilestonePath(' > 골라');

    const call = function(response) {
        console.log(response);
    };
    callAPI("GET", "/comments", null, call);
});
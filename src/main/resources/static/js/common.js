document.addEventListener("DOMContentLoaded", function() {
});
const callAPI = function(method, url, body, callBack) {
    console.log("[callAPI] START");
    console.log("[callAPI] Method : " + method);
    console.log("[callAPI] url : " + url);
    const xhr = new XMLHttpRequest();
    xhr.onload = function() {
        if(xhr.status === 200 || xhr.status === 201 || xhr.status === 204) {
            console.log("[callAPI] Response : " + xhr.responseText);
            console.log("[callAPI] END");
            callBack(xhr.responseText);
            return;
        }
        console.log("[callAPI] Error");
        console.log(xhr.responseText);
    };
    xhr.open(method, url)
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(body));
};

const setMilestonePath = function(path) {
    document.getElementById("milestone").textContent = path;
};

const getQueryString = function() {
    const urlSearchParams = new URLSearchParams(window.location.search);
    return Object.fromEntries(urlSearchParams.entries());
};
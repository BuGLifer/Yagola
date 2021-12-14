const callAPI = function(method, url, body) {
    console.log("[callAPI] START");
    console.log("[callAPI] Method : " + method);
    console.log("[callAPI] url : " + url);
    const xhr = new XMLHttpRequest();
    xhr.onload = function() {
        if(xhr.status === 200 || xhr.status ===201) {
            console.log("[callAPI] Response : " + xhr.responseText);
            console.log("[callAPI] END");
            return xhr.responseText;
        }
        console.log("[callAPI] Error");
        console.log(xhr.responseText);
    };
    xhr.open(method, url)
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(body));
};
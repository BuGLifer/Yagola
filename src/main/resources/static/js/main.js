document.addEventListener("DOMContentLoaded", function() {
    setMilestonePath('');
    callAPI('GET', 'http://yagola.co.kr/menu', '', parseResponse);
});

const parseResponse = function(response) {
    const menuList = JSON.parse(response);
    makeClone(menuList);
}

const initSlide = function(response) {
    console.log(response.content);
    let ul = document.getElementById('menuList');
    for (const menu of response.content) {
        console.log(menu)
        let li = document.createElement('li');
        li.classList.add('m10', 'w15vw', 'h20v', 'br24u');
        let a = document.createElement('a');
        a.href = '/menu/' + menu.seq;
        let img = document.createElement('img');
        img.src = menu.imgLink;
        a.appendChild(img);
        li.appendChild(a);
        let span = document.createElement('span');
        span.innerHTML = '<span class="fwb">' + menu.name + '</span></br>' + '<span>' + menu.price + '원</span>';
        a.appendChild(span);
        ul.appendChild(li);
    }

}

const makeClone = function(response) {
    initSlide(response)
    // currentIdx = 0;
    // for (let i = 0; i < slideCount; i++) {
    //     let cloneSlide = slide[i].cloneNode(true);
    //     slides.appendChild(cloneSlide);
    // }
    // for (let i = slideCount - 1; i >= 0; i-) {
    //     let cloneSlide = slide[i].cloneNode(true);
    //     slides.prepend(cloneSlide);
    // }
    // setInitialPos();
    // setTimeout(function () {
    //     slides.classList.add('animated');
    // }, 10)
}
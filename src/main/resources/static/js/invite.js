document.addEventListener("DOMContentLoaded", function() {
    setMilestonePath(' > 모집');
    setOrderCard();
    //callAPI('GET', 'http://localhost/comments', '', callBackFunc);
});

const createOrderCard = function(order) {
    let orderCardDiv = document.createElement('div');
    orderCardDiv.classList.add('orderCard');
    return orderCardDiv;
};

const setOrderCard = function() {
    let orderCardArea = document.getElementById('orderCardArea');
    orderCardArea.classList.add('orderCardArea');
    orderCardArea.append(createOrderCard(null));
};
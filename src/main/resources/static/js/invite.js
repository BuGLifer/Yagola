document.addEventListener("DOMContentLoaded", function() {
    setMilestonePath(' > 모집');
    setOrderCard();
    //callAPI('GET', 'http://localhost/comments', '', callBackFunc);
});

const createOrderCard = function(order) {
    console.log(order);
    let orderCardDiv = document.createElement('div');
    orderCardDiv.classList.add('orderCard');
    orderCardDiv.onclick = clickOrderCard;
    orderCardDiv.dataset.seq = order.seq;
    let restaurantInfoDiv = document.createElement('div');
    restaurantInfoDiv.classList.add('restaurantInfo');
    const restaurantInfo = order.restaurant;
    let restaurantI = document.createElement('i');
    restaurantI.classList.add('fas');
    restaurantI.classList.add('fa-utensils');
    restaurantInfoDiv.append(restaurantI);
    let restaurantItemSpan = document.createElement('span');
    restaurantItemSpan.textContent = restaurantInfo.name;
    restaurantInfoDiv.append(restaurantItemSpan);
    let guestInfoDiv = document.createElement('div');
    guestInfoDiv.classList.add('guestInfo');
    const hostInfo = order.host;
    let hostItemDiv = document.createElement('div');
    hostItemDiv.classList.add('hostInfo');
    let hostItemI = document.createElement('i');
    hostItemI.classList.add('fas');
    hostItemI.classList.add('fa-child');
    let hostItemSpan = document.createElement('span');
    hostItemSpan.textContent = hostInfo.nickName;
    hostItemDiv.append(hostItemI);
    hostItemDiv.append(hostItemSpan);
    guestInfoDiv.append(hostItemDiv);
    const guestInfo = order.guest;
    guestInfo.forEach(e => {
        let guestItemDiv = document.createElement('div');
        let guestItemI = document.createElement('i');
        guestItemI.classList.add('fas');
        guestItemI.classList.add('fa-user');
        let guestItemSpan = document.createElement('span');
        guestItemSpan.textContent = e.nickName;
        guestItemDiv.append(guestItemI);
        guestItemDiv.append(guestItemSpan);
        guestInfoDiv.append(guestItemDiv);
    });
    let orderStatusInfoDiv = document.createElement('div');
    orderStatusInfoDiv.classList.add('orderStatusInfo');
    let orderStatusDiv = document.createElement('div');
    orderStatusDiv.classList.add('orderStatus');
    let orderStatusI = document.createElement('i');
    orderStatusI.classList.add('fas');
    orderStatusI.classList.add('fa-flag');
    orderStatusDiv.append(orderStatusI);
    let orderStatusSpan = document.createElement('span');
    orderStatusSpan.textContent = order.status;
    orderStatusDiv.append(orderStatusSpan);
    let orderTimeDiv = document.createElement('div');
    orderTimeDiv.classList.add('orderTime');
    let orderTimeI = document.createElement('i');
    orderTimeI.classList.add('fas');
    orderTimeI.classList.add('fa-stopwatch-20');
    orderTimeDiv.append(orderTimeI);
    let orderTimeSpan = document.createElement('span');
    orderTimeSpan.textContent = order.createdTime;
    orderTimeDiv.append(orderTimeSpan);
    orderStatusInfoDiv.append(orderStatusDiv);
    orderStatusInfoDiv.append(orderTimeDiv);
    let orderInfoDiv = document.createElement('div');
    orderInfoDiv.classList.add('orderInfo');
    orderInfoDiv.append(restaurantInfoDiv);
    orderInfoDiv.append(orderStatusInfoDiv);
    orderCardDiv.append(orderInfoDiv);
    orderCardDiv.append(guestInfoDiv);
    return orderCardDiv;
};

const setOrderCard = function() {
    let orderCardArea = document.getElementById('orderCardArea');
    orderCardArea.classList.add('orderCardArea');
    const testOrder = {
        "seq" : 1
        , "status" : "ONLINE"
        , "createdTime" : "2021-12-18 13:00:00"
        , "host" : {
            "seq" : 1
            , "nickName" : "길로그"
        }
        , "guest" : [
            {
                "seq" : 2
                , "nickName" : "참가자1"
                , "menu" : {
                    "seq" : 1
                    , "name" : "닭가슴살"
                }
            }
            , {
                "seq" : 3
                , "nickName" : "참가자3"
                , "menu" : {
                    "seq" : 2
                    , "name" : "고구마"
                }
            }
            , {
                "seq" : 4
                , "nickName" : "참가자4"
                , "menu" : {
                    "seq" : 3
                    , "name" : "야채믹스"
                }
            }
        ]
        , "restaurant" : {
            "seq" : 1
            , "name" : "골목식당"
        }
    };
    orderCardArea.append(createOrderCard(testOrder));
};

const clickOrderCard = function() {
  alert(this.dataset.seq);
};
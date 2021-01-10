console.log('Test for running app successful!');
console.log('Host name: ', document.location.hostname );
console.log('Protocol : ', document.location.protocol );

// const url = 'ws' + "://" + document.location.hostname + ":8080";
const url = 'ws://localhost:8080/rgb';

let socket = new WebSocket(url);

const redSlider = document.getElementById("red");
const greenSlider = document.getElementById("green");
const blueSlider = document.getElementById("blue");

const redLog = document.getElementById("redLog");
const greenLog = document.getElementById("greenLog");
const blueLog = document.getElementById("blueLog");

const square = document.getElementById("result-color");
const rgbResultText = document.getElementById("result-text");

redSlider.addEventListener("change", updateValue);
greenSlider.addEventListener("change", updateValue);
blueSlider.addEventListener("change", updateValue);

function updateValue(e) {

    console.log("red: ", redSlider.value);
    console.log("green: ", greenSlider.value);
    console.log("blue: ", blueSlider.value);

    redLog.textContent = redSlider.value;
    greenLog.textContent = greenSlider.value;
    blueLog.textContent = blueSlider.value;


    const payload = {
        red: redSlider.value,
        green: greenSlider.value,
        blue: blueSlider.value
    }
    
    socket.send(payload);

    // square.style.backgroundColor = `rgb(${redSlider.value}, ${
    //     greenSlider.value
    //     }, ${blueSlider.value})`;
    
    // rgbResultText.textContent = `RGB color: (${redSlider.value}, ${
    //     greenSlider.value
    //     }, ${blueSlider.value})`
}

// let url = location.host == 'localhost' ?
//     'ws://localhost:8080/ws' : location.host == 'javascript.local' ?
//         `ws://javascript.local/article/websocket/chat/ws` : // dev integration with local site
//         `wss://javascript.info/article/websocket/chat/ws`; // prod integration with javascript.info


// send message from the form
// document.forms.publish.onsubmit = function () {
//     let outgoingMessage = this.message.value;

//     socket.send(outgoingMessage);
//     return false;
// };

// handle incoming messages
socket.onmessage = function (event) {
    let colorValues = event.data;
    consol.log(colorValues)
    updateSquareColor(JSON.parse(colorValues));
};

socket.onclose = event => console.log(`Closed ${event.code}`);

// show message in div#messages
function updateSquareColor(colorValues) {
    square.style.backgroundColor = `rgb(${colorValues.red}, ${
        colorValues.green
        }, ${colorValues.blue})`;

    rgbResultText.textContent = `RGB color: (${colorValues.red}, ${
        colorValues.green
        }, ${colorValues.blue})`
}


const url = 'ws://localhost:8080/rgb/app';
let socket = new WebSocket(url);

socket.onmessage = function (event) {
    updateSquareColor(JSON.parse(event.data));
};

socket.onclose = event => console.log(`Closed ${event.code}`);

const redSlider = selectElement("#red");
const greenSlider = selectElement("#green");
const blueSlider = selectElement("#blue");

listenForChange(redSlider);
listenForChange(greenSlider);
listenForChange(blueSlider);

function listenForChange(element) {
    element.addEventListener("change", updateValue);
}

function updateValue(e) {
    updateSliderLoggerValues();

    const payload = {
        red: redSlider.value,
        green: greenSlider.value,
        blue: blueSlider.value
    };
    socket.send(JSON.stringify(payload));
}

function updateSliderLoggerValues() {
    const redLog = selectElement(".redLog");
    const greenLog = selectElement(".greenLog");
    const blueLog = selectElement(".blueLog");

    redLog.textContent = redSlider.value;
    greenLog.textContent = greenSlider.value;
    blueLog.textContent = blueSlider.value;
}

function selectElement(selector) {
    return document.querySelector(selector);
}

function updateSquareColor(colorValues) {
    const square = selectElement(".square");
    const rgbResultText = selectElement(".square-text");

    square.style.backgroundColor = `rgb(${colorValues.red}, ${
        colorValues.green
        }, ${colorValues.blue})`;

    rgbResultText.textContent = `RGB color: (${colorValues.red}, ${
        colorValues.green
        }, ${colorValues.blue})`
}


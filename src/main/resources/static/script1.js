const collection = document.getElementsByClassName("nav-scroller-item");
for (let i = 0; i < collection.length; i++) {
	collection[i].addEventListener("mouseover", function() {
		let image = this.getElementsByTagName("img")[0];
		let attributeValue = image.getAttribute("src");
		let regex = /_grey\..+/i;
		let newAttributeValue = attributeValue.replace(regex, "_colored.svg");
		image.setAttribute("src", newAttributeValue);
		//console.log(attributeValue + " .. " + newAttributeValue );
	});

	collection[i].addEventListener("mouseout", function() {
		let image = this.getElementsByTagName("img")[0];
		let attributeValue = image.getAttribute("src");
		let regex = /_colored\..+/i;
		let newAttributeValue = attributeValue.replace(regex, "_grey.svg");
		image.setAttribute("src", newAttributeValue);
		//console.log(attributeValue + " .. " + newAttributeValue );
	});
}

// Popup window

const popup = document.querySelector(".popup");
const overlay = document.querySelector(".overlay");
const btnOpenPopup = document.querySelector(".open-popup");
const btnClosePopup = document.querySelector(".close-popup");
const popupText = document.querySelector('.popup-text')
//Coming from server header and text into popup
const coffeeInformation = (param) => {
	const request = new XMLHttpRequest();
	request.open('GET', 'https://restcountries.com/v3.1/name/' + param);
	data = request.send();
	// console.log(this.responseText);

	request.addEventListener('load', function() {
		// console.log(this.responseText);
		const [data] = JSON.parse(this.responseText)
		console.log(data);

		const html = `<div>
        <h2>${data.capital}</h2>
        <div>😍</div>
        <p>${data.continents[0]}
        </p>
        </div>`
			;
		popupText.insertAdjacentHTML('beforeend', html)
	});
}
coffeeInformation('italy');
// Open popup
btnOpenPopup.addEventListener("click", function() {
	popup.classList.remove("hidden");
	overlay.classList.remove("hidden");
});
// Close popup
btnClosePopup.addEventListener("click", function() {
	popup.classList.add("hidden");
	overlay.classList.add("hidden");
});

// Overlay layer
overlay.addEventListener("click", function() {
	popup.classList.add("hidden");
	overlay.classList.add("hidden");
});
function confirmSelection() {
	// Buttons coffee-size
	const btnOkCoffeeSize = document.querySelector(".btn-ok-coffee-size");
	const btnBigCoffeeIcon = document.querySelector(".coffee-size-big");
	const btnMediumCoffeeIcon = document.querySelector(".coffee-size-medium");
	const btnSmallCoffeeIcon = document.querySelector(".coffee-size-small");
	//
	if (btnBigCoffeeIcon) {
		btnBigCoffeeIcon.addEventListener("click", function() {
			console.log("click coffee Big");
			//btnOkCoffeeSize.classList.remove("btn-ok-coffee-size");
			btnOkCoffeeSize.style.display="block";
			return;
		});
	}
	//
	if (btnMediumCoffeeIcon) {
		btnMediumCoffeeIcon.addEventListener("click", function() {
			console.log("click coffee Medium");
			btnOkCoffeeSize.style.display="block";
			//btnOkCoffeeSize.classList.remove("btn-ok-coffee-size");
			return;
		});
	}
	//
	if (btnSmallCoffeeIcon) {
		btnSmallCoffeeIcon.addEventListener("click", function() {
			btnOkCoffeeSize.style.display="block";	
			//btnOkCoffeeSize.classList.remove("btn-ok-coffee-size");
			console.log("click coffee Small");
			return;
		});
	}
}
confirmSelection();

// iCON BUTTONS COFFEE SIZE
function hoverSizeCollection() {
	const collectionCoffeeBtn = document.getElementsByClassName("coffee-icon");
	for (let i = 0; i < collectionCoffeeBtn.length; i++) {
		collectionCoffeeBtn[i].addEventListener("mouseover", function() {
			let image = this.getElementsByTagName("img")[0];
			let attributeValue = image.getAttribute("src");
			let regex = /_notactive\..+/i;
			let newAttributeValue = attributeValue.replace(regex, "_active.svg");
			image.setAttribute("src", newAttributeValue);
			//console.log(attributeValue + " .. " + newAttributeValue );
		});

		collectionCoffeeBtn[i].addEventListener("mouseout", function() {
			let image = this.getElementsByTagName("img")[0];
			let attributeValue = image.getAttribute("src");
			let regex = /_active\..+/i;
			let newAttributeValue = attributeValue.replace(regex, "_notactive.svg");
			image.setAttribute("src", newAttributeValue);
			//console.log(attributeValue + " .. " + newAttributeValue );
		});
	}
}
hoverSizeCollection();
// Inserting elements with grocery
const collectionOfGrocery = Math.floor(Math.random() * 10) + 1;
const groceryBox = document.querySelector('.box-container');
const groceryBoxDOM = 'box-' + collectionOfGrocery;
console.log(collectionOfGrocery);
const textGroceryBox = ` <div class="box-container box1">
<div class="flex-container">
  <div class="flex-item-left item-img1"></div>
  <div class="flex-item-right">
    <h3>strawberry</h3>
    <p>Frosted Strawberry Donut Frozen Treat</p>
  </div>
  <div class="btn-container">
    <div class="flex-item-btn btn-add">
      <a class="btn-add btn" href="#"
        ><img src="img/icons/add_btn.svg" alt="button add" />
      </a>
    </div>
  </div>
</div>
</div>`
console.log(textGroceryBox);
for (let i = 0; i < collectionOfGrocery.length; i++) {
	while (i !== 0) {
		collectionOfGrocery = [textGroceryBox];
	}
}
import { createUserRequest } from './createUser.js';
import { createOrUpdateCart } from './createCart.js';
import { createOrUpdateOrder } from './createOrder.js';
import { getLoggedUser } from './loggedUser.js';
import { createFormLabelAndInput } from './inputForm.js';


var messageBox = document.getElementById("messageBox");
var contentBox = document.getElementsByClassName("box").item(0);
var menu = document.getElementsByClassName("nav-scroller").item(0);
var url = window.location.href;

var selectedEnterprise;
let menuItems = [];
var selectedMenu;
var selectedItem;
var order;
var orderedItem = { "item": "", "name": "", "size": "", "price": "", "currency": "" };
var cart;
var cartItems = [];
var user;

function switchWindow(address) {
	signUpUser();
	if (address.includes("cafe")) {
		getContentForMenu();
		getContentForEnterpriseMenu();
	} else if (address.includes("select")) {
		getContentForSelection();
	} else if (address.includes("cart")) {
		getContentForCart();
	} else if (address.includes("signUp")) {
		getContentForSignUp();
	} else if (address.includes("signIn")) {
		getContentForSignIn();
	} else if (address.match(/[htp]+:\/{2}[a-z]+:\d+\/takeaway\//)
		|| address.match(/[htp]+:\/{2}[a-z]+:\d+\/takeaway\/#/)
		|| address.includes("index")) {
		selectedMenu=undefined;
		menuItems = [];
		getContentForIndex();
	}
}
switchWindow(url);

window.addEventListener("hashchange", () => switchWindow(window.location.href));

function getContentForMenu() {
	menu.style.display = "block";
	removeAllChildrenOf(document.getElementsByClassName("nav-scroller-items").item(0));
	for (let menuId of selectedEnterprise.menus) {
		const menuItemsUrl = "http://localhost:8080/takeaway/v1/menu/" + menuId;
		let result = fetch(menuItemsUrl).then(response => response.json()).then(item => { createContentForMenu(item); return item; });
		menuItems.push(result);
	}

	if (typeof menuItems[0] !== "undefined") {
		selectedMenu = menuItems[0];
	}
}

//create element for menu tab
function createContentForMenu(promise) {
	let container = document.getElementsByClassName("nav-scroller-items").item(0);
	let link = document.createElement("a");
	let text = document.createElement("span");
	link.setAttribute("class", "nav-scroller-item item1");
	let wrapper = document.createElement("span");
	let picture = document.createElement("img");
	picture.setAttribute("src", "img/icons/cup_of_coffee_grey.svg");
	wrapper.appendChild(picture);
	text.innerHTML = promise.name;
	wrapper.appendChild(text);
	link.appendChild(wrapper);
	container.appendChild(link);
}


function getContentForEnterpriseMenu() {
	removeAllChildren();
	getEnterpriseHeaderStyle();

	if (typeof selectedMenu === "undefined") {
		return;
	}
	selectedMenu.then(res => {
		const menuItemsUrl = "http://localhost:8080/takeaway/v1/menu/" + res.id;
		fetch(menuItemsUrl).then(response => response.json()).then(promiseResult =>
			createContentForEnterpriseMenu(promiseResult));
	});
}

//create selection from menu items
function createContentForEnterpriseMenu(promise) {
	promise.items.forEach(item => {
		let boxContainer = document.createElement("div");
		let flexContainer = document.createElement("div");
		let picture = document.createElement("div");
		let menu = document.createElement("div");
		let btnContainer = document.createElement("div");
		let itemBtn = document.createElement("div");
		let itemBtnLink = document.createElement("a");
		let itemBtnImg = document.createElement("img");

		//flex-item-btn btn-add
		let name = document.createElement("h3");
		let description = document.createElement("p");

		boxContainer.setAttribute("class", "box-container box1");
		flexContainer.setAttribute("class", "flex-container");
		picture.setAttribute("class", "flex-item-left1 item-img1");
		menu.setAttribute("class", "flex-item-right");
		btnContainer.setAttribute("class", "btn-container");
		itemBtn.setAttribute("class", "flex-item-btn btn-add");
		itemBtnLink.setAttribute("class", "btn-add btn");
		itemBtnLink.addEventListener("click", function() {
			selectedItem = item;
			window.history.pushState("", "", "#selection");
			switchWindow(window.location.href);
		});
		itemBtnImg.setAttribute("src", "img/icons/add_btn.svg");
		itemBtnImg.setAttribute("alt", "button add");

		name.innerHTML = item.name;
		description.innerHTML = item.description;
		menu.appendChild(name);
		menu.appendChild(description);

		itemBtnLink.appendChild(itemBtnImg);
		itemBtn.appendChild(itemBtnLink);
		btnContainer.appendChild(itemBtn);

		picture.style.backgroundImage = "url('img/pict/coffee_2.jpg')";
		picture.style.backgroundPosition = "center";
		flexContainer.appendChild(picture);
		flexContainer.appendChild(menu);
		flexContainer.appendChild(btnContainer);
		boxContainer.appendChild(flexContainer);
		contentBox.appendChild(boxContainer);
	});
}

//update header picture and style for enterprise
function getEnterpriseHeaderStyle() {
	visibleSearchBar(true);
	removeFirstClassElement("cafe-name");
	removeFirstClassElement("btn-info");
	removeFirstClassElement("user");
	removeFirstClassElement("bussines");
	document.getElementsByClassName("logo-box").item(0).style.display = "none";
	let cafeName = document.createElement("div");
	cafeName.setAttribute("class", "cafe-name");
	let cafeNameValue = document.createElement("h1");
	cafeNameValue.innerHTML = selectedEnterprise.name;
	cafeName.appendChild(cafeNameValue);
	getNewHeaderStyle("cafe").appendChild(cafeName);

}

//get content for selected menu item
function getContentForSelection() {
	menu.style.display = "none";
	removeAllChildren();
	getHeaderForSelection();


	const enterpriseUrl = "http://localhost:8080/takeaway/v1/item/" + selectedItem.id;
	fetch(enterpriseUrl).then(response => response.json()).then(promise => createItemSelection(promise));
}

//(re)paint header for selection
function getHeaderForSelection() {
	document.getElementsByClassName("cafe-name").item(0).style.display = "none";
	let infoButton = document.createElement("button");
	removeFirstClassElement("btn-info");
	removeFirstClassElement("user");
	removeFirstClassElement("bussines");
	let image = document.createElement("img");
	infoButton.setAttribute("class", "btn-info open-popup");
	image.setAttribute("src", "img/icons/info_button.svg");
	image.setAttribute("alt", "info button");
	infoButton.appendChild(image);
	getNewHeaderStyle("selection").after(infoButton);

	visibleSearchBar(false);
}

//create item for chosen item from enterprise menu
function createItemSelection(promise) {

	let name = document.createElement("div");
	let nameHeading = document.createElement("h2");
	let stars = document.createElement("div");
	let starsImg = document.createElement("img");
	let description = document.createElement("div");
	let descriptionContent = document.createElement("p");
	let selectContainer = document.createElement("div");
	let boxIcons = document.createElement("div");
	let confirm = document.createElement("div");
	let okButton = document.createElement("button");
	nameHeading.innerHTML = promise.name;
	name.setAttribute("class", "name-item-notice");
	name.appendChild(nameHeading);
	stars.setAttribute("class", "stars-icon-item");
	starsImg.setAttribute("src", "img/icons/stars.svg");
	starsImg.setAttribute("alt", "stars");
	description.setAttribute("class", "notice-item");
	selectContainer.setAttribute("class", "select-container");
	boxIcons.setAttribute("class", "box-icons-coffee");
	confirm.setAttribute("class", "btn-ok-coffee-size");
	okButton.setAttribute("class", "ok");
	descriptionContent.innerHTML = promise.description;
	stars.appendChild(starsImg);
	description.appendChild(descriptionContent);
	selectContainer.appendChild(boxIcons);
	okButton.innerHTML = "Ok";
	okButton.addEventListener("click", async () => {
		orderedItem.item = selectedItem.id;
		orderedItem.name = selectedItem.name;

		cartItems.push(orderedItem);
		if (typeof cart === "undefined") {
			cart = { "payment": "CARD", "user": user.id };
		}
		cart = await createOrUpdateCart(cart);
		if (typeof order === "undefined") {
			order = { "items": cartItems, "cartId": cart.id };
		} else {
			order.items = cartItems;
		}
		order = await createOrUpdateOrder(order);
	});
	if (typeof user !== "undefined") {
		confirm.appendChild(okButton);
	} else {
		showWarnMessage(true, "Log in first!");
	}
	selectContainer.appendChild(confirm);
	promise.calc.forEach(content =>
		createSizeSelection(boxIcons, content)
	);
	contentBox.appendChild(name);
	contentBox.appendChild(stars);
	contentBox.appendChild(description);
	contentBox.appendChild(selectContainer);

	hoverSizeCollection();
	confirmSelection();
}

//set behavior on item size selection 
function createSizeSelection(element, content) {
	let button = document.createElement("button");
	button.addEventListener("click", () => {
		orderedItem = {};
		orderedItem.size = content.size;
		orderedItem.price = content.price;
		orderedItem.currency = content.currency;
	});
	let image = document.createElement("img");
	let notice = document.createElement("div");
	let noticeContent = document.createElement("p");
	if (content.size.length !== 0 && content.size === "SMALL") {
		button.setAttribute("class", "small coffee-icon coffee-size-small");
		notice.setAttribute("class", "notice-coffee-icon small-notice");
		image.setAttribute("src", "img/icons/small_coffee_icon_notactive.svg");
	} else if (content.size === "MEDIUM") {
		button.setAttribute("class", "medium coffee-icon coffee-size-medium");
		notice.setAttribute("class", "notice-coffee-icon medium-notice");
		image.setAttribute("src", "img/icons/medium_coffee_icon_notactive.svg");
	} else {
		button.setAttribute("class", "big coffee-icon coffee-size-big");
		notice.setAttribute("class", "notice-coffee-icon big-notice");
		image.setAttribute("src", "img/icons/big_coffee_icon_notactive.svg");
	}
	image.setAttribute("alt", "coffee_icon");
	noticeContent.innerHTML = content.size;
	notice.appendChild(noticeContent);
	button.appendChild(image);
	button.appendChild(notice);
	element.appendChild(button);
}
async function getContentForCart() {
	menu.style.display = "none";
	getHeaderIndex();
	removeAllChildren();
	visibleSearchBar(false);


	if (typeof user === "undefined") {
		showWarnMessage(true, "Nothing to show, log in first !");
		console.log("user must be signed in");
		return;
	}
	const cartUrl = "http://localhost:8080/takeaway/v1/cart/user/" + user.id;
	cart = await fetch(cartUrl).then(response => response.json());
	const orderUrl = "http://localhost:8080/takeaway/v1/order/cart/" + cart.id;
	order = await fetch(orderUrl).then(response => response.json());
	cartItems = [];
	for (let item of order.items) {
		let itemUrl = "http://localhost:8080/takeaway/v1/item/" + item.item;
		cartItems.push(await fetch(itemUrl).then(response => response.json()).then(i => {
			orderedItem = {};
			orderedItem.item = i.id;
			orderedItem.name = i.name;
			orderedItem.size = item.size;
			orderedItem.price = item.price;
			orderedItem.currency = i.calc[0].currency;
			return orderedItem;
		}));
	}

	createOrderForCart();
}



function createOrderForCart() {
	let boxContainer = createOrderTitle();
	let boxContent = createOrderContent(cartItems);
	let boxTotal = createOrderTotal();
	contentBox.appendChild(boxContainer);
	contentBox.appendChild(boxContent);
	contentBox.appendChild(boxTotal);
}


function createOrderTitle() {
	let boxContainer = document.createElement("div");
	let flexContainer = document.createElement("div");
	let flexContainerLeftContent = document.createElement("div");
	let flexContainerRightContent = document.createElement("div");
	let flexContainerHeading = document.createElement("h3");
	let flexContainerImage = document.createElement("img");

	boxContainer.setAttribute("class", "box-container_add_to_card");
	flexContainer.setAttribute("class", "flex-container_add_to_card");
	flexContainerLeftContent.setAttribute("class", "flex-item-left_add_to_card");
	flexContainerHeading.innerHTML = "Add to card";
	flexContainerLeftContent.appendChild(flexContainerHeading);
	flexContainerRightContent.setAttribute("class", "flex-item-right_add_to_card");
	flexContainer.appendChild(flexContainerLeftContent);
	flexContainerImage.setAttribute("src", "img/icons/my_order.svg");
	flexContainerImage.setAttribute("alt", "my order");
	flexContainerImage.setAttribute("class", "my_order");
	flexContainerRightContent.appendChild(flexContainerImage);
	flexContainer.appendChild(flexContainerRightContent);
	boxContainer.appendChild(flexContainer);
	return boxContainer;
}

function createOrderContent(orderItems) {
	let boxContainer = document.createElement("div");
	boxContainer.setAttribute("class", "box-container_add_to_card");
	orderItems.forEach(item => {
		boxContainer.appendChild(createOrderItemRow(item));
	});
	return boxContainer;
}

function createOrderItemRow(item) {
	let row = document.createElement("div");
	row.setAttribute("class", "flex-container_add_to_card");
	let closeBtnContainer = document.createElement("div");
	let closeButton = document.createElement("button");
	let closeButtonImage = document.createElement("img");
	closeButtonImage.setAttribute("src", "img/icons/close_button.svg");
	closeButtonImage.setAttribute("alt", "close button");
	closeButtonImage.style.cursor = "pointer";
	closeButton.setAttribute("class", "close");
	closeButtonImage.addEventListener("click", async () => {
		const index = cartItems.indexOf(item);
		if (index > -1) {
			cartItems.splice(index, 1);
			row.remove();
			order.items = cartItems;
			order = await createOrUpdateOrder(order);
		}
	});
	closeButton.appendChild(closeButtonImage);
	closeBtnContainer.appendChild(closeButton);
	let pictureContainer = document.createElement("div");
	let picture = document.createElement("img");
	picture.setAttribute("src", "img/icons/ordered_latte.png");
	pictureContainer.appendChild(picture);

	row.appendChild(closeBtnContainer);
	row.appendChild(pictureContainer);
	row.appendChild(createOrderItemRowTextCell(item.name));
	row.appendChild(createOrderItemRowTextCell(item.size));
	row.appendChild(createOrderItemRowTextCell(1));
	row.appendChild(createOrderItemRowTextCell(item.price));
	row.appendChild(createOrderItemRowTextCell(item.currency));

	return row;
}

function createOrderItemRowTextCell(textContent) {
	let textContainer = document.createElement("div");
	let text = document.createElement("p");
	text.innerHTML = textContent;
	textContainer.appendChild(text);
	return textContainer;
}

function createOrderTotal() {
	let boxContainer = document.createElement("div");
	boxContainer.setAttribute("class", "box-container_add_to_card");
	let totalImageContainer = document.createElement("div");
	let totalImage = document.createElement("img");
	totalImage.setAttribute("src", "img/icons/Total.svg");
	totalImageContainer.appendChild(totalImage);
	let totalDescriptionContainer = document.createElement("div");
	totalDescriptionContainer.setAttribute("class", "text_total");
	let totalDescription = document.createElement("h3");
	totalDescription.innerHTML = "Total";
	totalDescriptionContainer.appendChild(totalDescription);
	let totalPriceContainer = document.createElement("div");
	totalPriceContainer.setAttribute("class", "text_price");
	let totalPrice = document.createElement("h3");
	totalPrice.innerHTML = cartItems.reduce((previousValue, currentValue) => previousValue + currentValue.price, 0,) + " " + cartItems[0].currency;
	totalDescriptionContainer.appendChild(totalPrice);
	let payButton = document.createElement("button");
	payButton.setAttribute("class", "btn-pay");
	payButton.innerHTML = "Pay";
	boxContainer.appendChild(totalImageContainer);
	boxContainer.appendChild(totalDescriptionContainer);
	boxContainer.appendChild(totalPriceContainer);
	boxContainer.appendChild(payButton);
	return boxContainer;
}


function getContentForSignUp() {
	menu.style.display = "none";
	removeAllChildren();
	getHeaderForSignUp();
	visibleSearchBar(false);
	createContentForSignUp();
	createUserRequest();
}

//(re)paint header for default page
function getHeaderForSignUp() {
	visibleSearchBar(false);
	getNewHeaderStyle();
	removeFirstClassElement("cafe-name");
	removeFirstClassElement("btn-info");
	removeFirstClassElement("user");
	removeFirstClassElement("bussines");
	let headerContainer = document.getElementsByClassName("container").item(0);
	let ordinary = document.createElement("button");
	ordinary.setAttribute("class", "btn-sign user");
	ordinary.innerHTML = "Users";
	let bussines = document.createElement("button");
	bussines.setAttribute("class", "btn-sign bussines");
	bussines.innerHTML = "Business";
	headerContainer.appendChild(bussines);
	headerContainer.appendChild(ordinary);
	document.getElementsByClassName("logo-box").item(0).style.display = "block";
}

//create content for sign up form 
function createContentForSignUp() {
	let titleForm = document.createElement("div");
	titleForm.setAttribute("class", "container signIn-text");
	let titleFormHeader = document.createElement("h3");
	titleFormHeader.innerHTML = "Let's sign you up!";
	titleForm.appendChild(titleFormHeader);
	contentBox.appendChild(titleForm);
	let formContainer = document.createElement("div");
	formContainer.setAttribute("class", "container formSignInGapAfter");
	let form = document.createElement("form");
	form.setAttribute("id", "createUser");
	form.setAttribute("method", "post");
	form.setAttribute("action", "#signUp");
	form.appendChild(createFormLabelAndInput({
		"label": "E-mail",
		"inputId": "email",
		"inputType": "text",
		"inputName": "email",
		"required": true,
		"inputPlaceholder": "Your e-mail.."
	}));
	form.appendChild(createFormLabelAndInput({
		"label": "Name",
		"inputId": "name",
		"inputType": "text",
		"inputName": "firstName",
		"required": false,
		"inputPlaceholder": "Your name.."
	}));
	form.appendChild(createFormLabelAndInput({
		"label": "Surname",
		"inputId": "surname",
		"inputType": "text",
		"inputName": "lastName",
		"required": false,
		"inputPlaceholder": "Your surname.."
	}));
	form.appendChild(createFormLabelAndInput({
		"label": "Birthday",
		"inputId": "birthday",
		"inputType": "date",
		"inputName": "birthday",
		"required": false,
		"inputPlaceholder": null
	}));
	form.appendChild(createFormLabelAndInput({
		"label": "Phone",
		"inputId": "phone",
		"inputType": "text",
		"inputName": "phone",
		"required": false,
		"inputPlaceholder": "Your phone number.."
	}));
	form.appendChild(createFormLabelAndInput({
		"label": "Password",
		"inputId": "password",
		"inputType": "password",
		"inputName": "password",
		"required": true,
		"inputPlaceholder": "Your password.."
	}));
	//submit button
	let submit = document.createElement("input");
	submit.setAttribute("type", "submit");
	submit.setAttribute("value", "Continue");
	form.appendChild(submit);
	formContainer.appendChild(form);
	contentBox.appendChild(formContainer);
}



function getContentForSignIn() {
	menu.style.display = "none";
	removeAllChildren();
	getHeaderIndex();
	visibleSearchBar(false);
	createContentForSignIn();
	displayValidationForLogin();
}

function createContentForSignIn() {
	let imageContainer = document.createElement("div");
	let image = document.createElement("img");
	image.setAttribute("src", "img/icons/signIn.svg");
	image.setAttribute("alt", "sign in");
	image.setAttribute("class", "img-sign-in container");
	imageContainer.appendChild(image);
	let titleContainer = document.createElement("div");
	titleContainer.setAttribute("class", "container signIn-text");
	let title = document.createElement("h3");
	title.innerHTML = "Let's sign you in!";

	let formContainer = document.createElement("div");
	formContainer.setAttribute("class", "container");
	let form = document.createElement("form");
	form.setAttribute("action", "login");
	form.enctype = "application/x-www-form-urlencoded";
	form.setAttribute("method", "post");
	form.appendChild(createFormLabelAndInput({
		"label": "E-mail",
		"inputId": "email",
		"inputType": "text",
		"inputName": "username",
		"required": true,
		"inputPlaceholder": "Your e-mail.."
	}));
	form.appendChild(createFormLabelAndInput({
		"label": "Password",
		"inputId": "password",
		"inputType": "password",
		"inputName": "password",
		"required": true,
		"inputPlaceholder": "Your password.."
	}));
	let signInButton = document.createElement("input");
	signInButton.setAttribute("type", "submit")
	signInButton.setAttribute("value", "Continue")
	form.appendChild(signInButton);
	titleContainer.appendChild(title);
	formContainer.appendChild(form);
	let signUpTitleContainer = document.createElement("div");
	signUpTitleContainer.setAttribute("class", "container signIn-text");
	let signUpTitle = document.createElement("h3");
	signUpTitle.innerHTML = "or create account...";
	signUpTitleContainer.addEventListener("click", () => {
		window.history.pushState("", "", "#signUp");
		switchWindow(window.location.href);
	});
	signUpTitleContainer.style.cursor = "pointer";
	signUpTitleContainer.appendChild(signUpTitle);
	contentBox.appendChild(imageContainer);
	contentBox.appendChild(titleContainer);
	contentBox.appendChild(formContainer);
	contentBox.appendChild(signUpTitleContainer);
}

function displayValidationForLogin() {
	document.getElementById("email").style.border = "";
	document.getElementById("password").style.border = "";
	if (url.includes("error")) {
		document.getElementById("email").style.border = "3px solid red";
		document.getElementById("password").style.border = "3px solid red";
		url = url.split("?")[0];
	}
}

//get content for defaut page - all enterprises
function getContentForIndex() {
	menu.style.display = "none";
	getHeaderIndex();
	removeAllChildren();

	const enterpriseUrl = "http://localhost:8080/takeaway/v1/enterprise/all";
	fetch(enterpriseUrl).then(response => response.json()).then(promise => createItemForEnterprise(promise));
}

//(re)paint header for default page
function getHeaderIndex() {
	visibleSearchBar(true);
	getNewHeaderStyle();
	removeFirstClassElement("cafe-name");
	removeFirstClassElement("btn-info");
	removeFirstClassElement("user");
	removeFirstClassElement("bussines");
	document.getElementsByClassName("logo-box").item(0).style.display = "block";
}




//create items for enterprise list
function createItemForEnterprise(promise) {
	promise.forEach(item => {
		let boxContainer = document.createElement("div");
		let flexContainer = document.createElement("div");
		let picture = document.createElement("div");
		let enterprise = document.createElement("div");
		let name = document.createElement("h2");
		let about = document.createElement("p");

		boxContainer.setAttribute("class", "box-container box1");
		flexContainer.setAttribute("class", "flex-container");
		picture.setAttribute("class", "flex-item-left1 item-img1");
		enterprise.setAttribute("class", "flex-item-right");
		name.innerHTML = item.name;
		about.innerHTML = item.about;
		enterprise.appendChild(name);
		enterprise.appendChild(about);
		flexContainer.appendChild(picture);
		flexContainer.appendChild(enterprise);
		flexContainer.addEventListener("click", () => {
			selectedEnterprise = item;
			window.history.pushState("", "", "#cafe");
			switchWindow(window.location.href);
		});
		boxContainer.appendChild(flexContainer);
		contentBox.appendChild(boxContainer);
	});
}
//remove all items from content box
function removeAllChildren() {
	while (contentBox.firstChild) {
		contentBox.removeChild(contentBox.lastChild);
	}
}

function removeAllChildrenOf(element) {
	while (element.firstChild) {
		element.removeChild(element.lastChild);
	}
}

//select header, depending on route
function getNewHeaderStyle(type) {
	let header = document.getElementsByClassName("header").item(0);
	if (type === "selection") {
		header.style.backgroundImage = "url('img/pict/coffee1_cover.jpg')";
		header.style.backgroundSize = "cover";
		header.style.backgroundPosition = "bottom";
		header.style.backgroundRepeat = "no-repeat";
	} else if (type === "cafe") {
		header.style.backgroundImage = "url('img/pict/donut_cover.jpg')";
		header.style.backgroundSize = "cover";
		header.style.backgroundPosition = "center";
	} else {
		header.style.backgroundImage = "inherit";
		header.style.backgroundSize = "inherit";
		header.style.backgroundPosition = "inherit";
		header.style.backgroundRepeat = "inherit";
	}
	return header;
}

//show/hide search bar
function visibleSearchBar(expr) {
	let searchBar = document.getElementsByClassName("search-bar").item(0);
	if (expr) {
		searchBar.style.visibility = "visible";
		searchBar.style.display = "inherit";
	}
	else {
		searchBar.style.display = "none";
	}
}

function showWarnMessage(show, message) {
	if (show) {
		messageBox.style.display = "block";
		messageBox.innerHTML = message;
		messageBox.style.backgroundColor = "#ffcc00";
		setTimeout(() => {
			messageBox.style.display = "none";
		}, 3000);
	} else {
		messageBox.style.display = "none";
		messageBox.innerHTML = "";
	}
}

async function signUpUser() {
	user = await getLoggedUser();
	let content = document.getElementsByClassName("profile").item(0).children.item(0);
	let username = content.getElementsByTagName("p").item(0);
	if (typeof user !== "undefined" && typeof user.firstName !== "undefined") {
		content.setAttribute("href", "logout");
		username.innerHTML = user.firstName + " " + user.lastName;
	}
}

//remove existing class element
function removeFirstClassElement(className) {
	if (document.getElementsByClassName(className).length !== 0) {
		document.getElementsByClassName(className).item(0).remove();
	}
}






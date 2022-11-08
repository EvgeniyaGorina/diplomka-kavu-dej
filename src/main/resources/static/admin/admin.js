import { getLoggedUser } from '../loggedUser.js';
import { createFormLabelAndInput } from '../inputForm.js';

var navbar = document.getElementById("navbar");
var box = document.getElementById("box");
var content = document.getElementById("content");
var url = window.location.href;
var items = [];
var item = { "name": "", "description": "" };
var user;
var menu = {"id":"", "name": "" };
var enterprise = { "id": "", "about": "", "name": "", "employees": [], "menus": [] };

async function supplyUser() {
	const res = await getLoggedUser();
	return res;
}

user = await supplyUser();
console.log(user);
if (typeof user === "undefined") {
	window.location.href = "http://localhost:8080/takeaway/";
}
switchWindow(url);

function switchWindow(url) {
	loadEnterprise();
	if (url.includes("cr_enterprise")) {
		box.style.display = "none";
		createEnterpriseForm();
	} else if (url.includes("cr_menu")) {
		box.style.display = "none";
		createMenuForm();
	} else if (url.includes("cr_user")) {
		box.style.display = "none";
		createUserForm();
	} else if (url.includes("cr_items")) {
		box.style.display = "none";
		createItemsForm();
	} else {
		removeAllChildren();
		if (!document.getElementById("username")) {
			let name = document.createElement("a");
			name.setAttribute("id", "username");
			name.innerHTML = user.email;
			name.style.paddingRight = "2px";
			name.style.borderRight = "1px solid black";
			navbar.prepend(name);
		}
		box.style.display = "flex";
	}
}

async function loadEnterprise() {
	if (!user.enterpriseId) {
		return;
	}

	for (let element of document.getElementsByTagName("a")) {
		if (element.innerHTML === "Create enterprise") {
			element.style.display = "none";
		}
	}
	const enterpriseUrl = "http://localhost:8080/takeaway/v1/enterprise/" + user.enterpriseId;
	const result = await fetch(enterpriseUrl).then(response => response.json());
	enterprise = result;
	console.log(enterprise);
}

function createEnterpriseForm() {
	removeAllChildren();
	let form = document.createElement("form");
	form.appendChild(createFormLabelAndInput(
		{
			"label": "Description",
			"inputId": "about",
			"inputType": "text",
			"inputName": "about",
			"required": true,
			"inputPlaceholder": "About enterprise.."
		}
	));
	form.appendChild(createFormLabelAndInput(
		{
			"label": "Name",
			"inputId": "name",
			"inputType": "text",
			"inputName": "name",
			"required": true,
			"inputPlaceholder": "Enterprise name.."
		}
	));
	let submit = document.createElement("input");
	submit.setAttribute("type", "submit");
	submit.setAttribute("value", "Create");
	form.addEventListener("submit", createOrUpdateEnterprise);
	form.appendChild(submit);
	content.appendChild(form);
}


async function createOrUpdateEnterprise(e) {
	if (e.preventDefault) e.preventDefault();

	if (!enterprise.id) {
		const url = "http://localhost:8080/takeaway/v1/enterprise";
		const data = enterprise;
		const response = await fetch(url, {
			method: 'POST', // *GET, POST, PUT, DELETE, etc.
			mode: 'cors', // no-cors, *cors, same-origin
			cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
			credentials: 'include', // include, *same-origin, omit
			headers: {
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': 'POST,PATCH,OPTIONS'
			},
			redirect: 'follow', // manual, *follow, error
			referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
			body: JSON.stringify(data) // body data type must match "Content-Type" header
		});
		await response.json();
	} else {
		const url = "http://localhost:8080/takeaway/v1/enterprise";
		const data = enterprise;
		const response = await fetch(url, {
			method: 'PUT', // *GET, POST, PUT, DELETE, etc.
			mode: 'cors', // no-cors, *cors, same-origin
			cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
			credentials: 'include', // include, *same-origin, omit
			headers: {
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': 'POST,PATCH,OPTIONS'
			},
			redirect: 'follow', // manual, *follow, error
			referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
			body: JSON.stringify(data) // body data type must match "Content-Type" header
		});
		await response.json();
	}
}

function createMenuForm() {
	removeAllChildren();
	let form = document.createElement("form");
	form.appendChild(createFormLabelAndInput(
		{
			"label": "Name",
			"inputId": "name",
			"inputType": "text",
			"inputName": "name",
			"required": true,
			"inputPlaceholder": "Menu name.."
		}
	));

	let submit = document.createElement("input");
	submit.setAttribute("type", "submit");
	submit.setAttribute("value", "Create");
	form.addEventListener("submit", async function (e) {
		const menuResponse = await createMenu(e).then(response => response.json());
		menu = menuResponse;
		console.log(menuResponse);
		enterprise.menus.push(menu.id);
		createOrUpdateEnterprise(e);
	});
	form.appendChild(submit);
	content.appendChild(form);
}

async function createMenu(e) {
	if (e.preventDefault) e.preventDefault();
	loadMenuFormValues();

	const url = "http://localhost:8080/takeaway/v1/menu";
	const data = menu;
	const response = await fetch(url, {
		method: 'POST', // *GET, POST, PUT, DELETE, etc.
		mode: 'cors', // no-cors, *cors, same-origin
		cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
		credentials: 'include', // include, *same-origin, omit
		headers: {
			'Content-Type': 'application/json',
			'Access-Control-Allow-Origin': '*',
			'Access-Control-Allow-Methods': 'POST,PATCH,OPTIONS'
		},
		redirect: 'follow', // manual, *follow, error
		referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
		body: JSON.stringify(data) // body data type must match "Content-Type" header
	});
	return response;
}

function createUserForm() {
	removeAllChildren();
	let form = document.createElement("form");
	const enterpriseUrl = "http://localhost:8080/takeaway/v1/enterprise/all";
	fetch(enterpriseUrl).then(response => response.json()).then(promise => promise.forEach(e => {

		let wrap = document.createElement("div");
		let label = document.createElement("label");
		label.innerHTML = e.name;
		label.setAttribute("for", e.id);
		wrap.appendChild(label);
		wrap.appendChild(createFormLabelAndInput(
			{
				"label": e.name,
				"inputId": e.id,
				"inputType": "radio",
				"inputName": "enterprise",
				"required": true,
				"value": e.id
			}
		));
		form.prepend(wrap);
	}));
	let submit = document.createElement("input");
	submit.setAttribute("type", "submit");
	submit.setAttribute("value", "Create");
	form.addEventListener("submit", updateUser);
	form.appendChild(submit);
	content.appendChild(form);
}

async function updateUser(e) {
	if (e.preventDefault) e.preventDefault();
	user.enterpriseId = document.querySelector('input[name="enterprise"]:checked').value;

	const url = "http://localhost:8080/takeaway/v1/user";
	const data = {};
	data.id = user.id;
	data.firstName = user.firstName;
	data.lastName = user.lastName;
	data.birthday = user.birthday;
	data.enterpriseId = user.enterpriseId;
	data.email = user.email;
	data.password = user.password;
	data.role = user.role;
	const response = await fetch(url, {
		method: 'PUT', // *GET, POST, PUT, DELETE, etc.
		mode: 'cors', // no-cors, *cors, same-origin
		cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
		credentials: 'include', // include, *same-origin, omit
		headers: {
			'Content-Type': 'application/json',
			'Access-Control-Allow-Origin': '*',
			'Access-Control-Allow-Methods': 'POST,PATCH,OPTIONS'
		},
		redirect: 'follow', // manual, *follow, error
		referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
		body: JSON.stringify(data) // body data type must match "Content-Type" header
	});
	await response.json();
}


function createItemsForm() {
	removeAllChildren();
	let form = document.createElement("form");
	form.appendChild(createFormLabelAndInput(
		{
			"label": "",
			"inputId": "description",
			"inputType": "text",
			"inputName": "description",
			"required": true,
			"inputPlaceholder": "Item description.."
		}
	));
	form.appendChild(createFormLabelAndInput(
		{
			"label": "",
			"inputId": "name",
			"inputType": "text",
			"inputName": "name",
			"required": true,
			"inputPlaceholder": "Item name.."
		}
	));

	let submit = document.createElement("input");
	submit.setAttribute("type", "submit");
	submit.setAttribute("value", "Create");
	form.addEventListener("submit", createItem);
	form.appendChild(submit);
	content.appendChild(form);
}


async function createItem(e) {
	if (e.preventDefault) e.preventDefault();
	loadItemFormValue();

	const url = "http://localhost:8080/takeaway/v1/item";
	const data = item;
	const response = await fetch(url, {
		method: 'POST', // *GET, POST, PUT, DELETE, etc.
		mode: 'cors', // no-cors, *cors, same-origin
		cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
		credentials: 'include', // include, *same-origin, omit
		headers: {
			'Content-Type': 'application/json',
			'Access-Control-Allow-Origin': '*',
			'Access-Control-Allow-Methods': 'POST,PATCH,OPTIONS'
		},
		redirect: 'follow', // manual, *follow, error
		referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
		body: JSON.stringify(data) // body data type must match "Content-Type" header
	});
	await response.json();
}

function loadItemFormValue() {
	item.name = document.getElementById("name").value;
	item.description = document.getElementById("description").value
}

function loadMenuFormValues() {
	menu.name = document.getElementById("name").value;
}

function removeAllChildren() {
	while (content.firstChild) {
		content.removeChild(content.lastChild);
	}
}

window.addEventListener("hashchange", () => switchWindow(window.location.href));



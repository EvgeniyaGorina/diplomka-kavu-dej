export function createUserRequest() {

	const form = document.getElementById("createUser");
	var formChildren = document.getElementById("createUser").children;
	var businessBtn = document.getElementsByClassName("bussines").item(0);
	var userBtn = document.getElementsByClassName("user").item(0);
	var checkRole = "USER";

	businessBtn.addEventListener("click", () => {
		businessBtn.style.borderBottom = "2px solid #1cbaba";
		businessBtn.style.color = "#db2b75";
		businessBtn.style.fontWeight = "700";
		userBtn.style.borderBottom = "2px solid white";
		userBtn.style.color = "black";
		userBtn.style.fontWeight = "normal";
		checkRole = "USER_OWNER";
	});

	userBtn.addEventListener("click", () => {
		userBtn.style.borderBottom = "2px solid #1cbaba";
		userBtn.style.color = "#db2b75";
		userBtn.style.fontWeight = "700";
		businessBtn.style.borderBottom = "2px solid white";
		businessBtn.style.color = "black";
		businessBtn.style.fontWeight = "normal";
		checkRole = "USER";
	});


	if (form.attachEvent) {
		form.attachEvent("submit", createUser);
	} else {
		form.addEventListener("submit", createUser);
	}


	async function createUser(e) {
		if (e.preventDefault) e.preventDefault();

		const url = "http://localhost:8080/takeaway/v1/user";
		const data = JSON.parse(prepareJsonString());
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
		}).then(res => { window.location.href = "http://localhost:8080/takeaway/#signIn" });
		return response;
	}

	function prepareJsonString() {
		let jsonString = "{";
		let confident = ["email", "password", "phone"];
		let confidentMap = new Map();
		jsonString += fillMainParams(confidentMap, confident);
		if (confidentMap.size >= 2) {
			jsonString += "\"credentials\": {";
			jsonString += "\"role\":" + "\"" + checkRole + "\",";
			let count = 0;
			confidentMap.forEach((value, key) => {
				count++;
				jsonString += "\"" + key + "\":\"" + value + "\"";
				if (count % confidentMap.size != 0) {
					jsonString += ",";
				}
			});
			//TODO remove for testing only
			jsonString += "}";
		}
		jsonString += "}";
		return jsonString;
	}


	function fillMainParams(map, confident) {
		let jsonString = "";
		for (let i = 0; i < formChildren.length; i++) {
			let child = formChildren[i];
			if (child.nodeName === 'INPUT') {
				if (child.getAttribute("type") === "submit") {
					continue;
				}
				if (child && !confident.includes(child.getAttribute("name"))) {
					jsonString += "\"" +
						child.getAttribute("name") + "\":\"" + child.value + "\", ";
				} else {
					map.set(child.getAttribute("name"), child.value);
				}
			} else {
				continue;
			}
		}
		return jsonString;
	}
}

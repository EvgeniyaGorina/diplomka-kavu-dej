export async function getLoggedUser() {
	const loggedUserUrl = "http://localhost:8080/takeaway/v1/user";
	let user = await fetch(loggedUserUrl)
		.then(response => {
			if (response.status >= 400 && response.status < 600) {
				throw new Error("Bad response from server");
			} else {
				return response.json();
			}
		})
		.catch(error => { console.error(error.message) });
		return user;	
}
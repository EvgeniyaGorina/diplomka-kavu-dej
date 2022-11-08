//create input and label for sign up form
export function createFormLabelAndInput(params) {
	let label = document.createElement("label");
	label.setAttribute("class", "inputSignIn");
	label.setAttribute("for", params.inputId);
	label.innerHTML = params.label;
	let input = document.createElement("input");
	input.setAttribute("type", params.inputType);
	input.setAttribute("id", params.inputId);
	input.setAttribute("name", params.inputName);
	if (params.inputPlaceholder !== null) {
		input.setAttribute("placeholder", params.inputPlaceholder);
	}
	if (params.value) {
		input.setAttribute("value", params.value);
	}
	input.required = params.required;
	input.after(label);
	return input;
}
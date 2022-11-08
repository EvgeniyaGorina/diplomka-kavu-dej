const routes = {
    404: {
        template: "404.html",
        title: "404",
    },
    "/": {
        template: "index.html",
        title: "Home",
    },
    cafe: {
        template: "cafe.html",
        title: "Cafe",
    },
    select: {
        template: "select.html",
        title: "Selection",
    },
};


const locationHandler = () => {
    // get the url path, replace hash with empty string
    var location = window.location.hash.replace("#", "");
    // if the path length is 0, set it to primary page route
    if (location.length == 0) {
        location = "/";
    }
    // get the route object from the routes object
    const route = routes[location] || routes["404"];
    // set the title of the document to the title of the route
    document.title = route.title;

};

// create a function that watches the hash and calls the urlLocationHandler
window.addEventListener("hashchange", locationHandler);
// call the urlLocationHandler to load the page
locationHandler();

let main_img_container = document.getElementsByClassName('main-img')[0];
let main_img = main_img_container.getElementsByTagName('img')[0];
let preview_row = document.getElementsByClassName('preview-row')[0];
let small_img = preview_row.getElementsByTagName('img');

preview_row.onclick = function (event) {
    if (event.target.nodeName === "IMG") {
        main_img.src = event.target.src;

        for (let img of small_img) {
            img.classList.remove("selected-img");
        }

        event.target.classList.add("selected-img");

        return false;
    }
}

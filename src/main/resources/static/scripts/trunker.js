export function truncate_cards() {
    let cards = document.getElementsByClassName('card-info');

    for (let card_info of cards) {
        if(card_info.innerText.length < 100) {
            continue;
        }

        card_info.innerText = card_info.innerText.substr(0, 197) + '...';
    }
}

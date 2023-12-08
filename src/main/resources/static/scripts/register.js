function generateString() {
    let abc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    let rs = "";

    while (rs.length < 5) {
        rs += abc[Math.floor(Math.random() * abc.length)];
    }

    return rs
}

let capcha = document.getElementById('capcha');
let register_form = document.getElementById('registration');

register_form.style.display = 'none';

let capcha_question = document.getElementById('question');
let capcha_answer = document.getElementById('answer');
let submit_capcha_button = document.getElementById('submit-capcha');

let answer = generateString();
capcha_question.textContent = 'Введите строку "' + answer + '"';

let attempts = 0;

submit_capcha_button.onclick = function () {
    if(attempts === 0) {
        if(capcha_answer.value === answer) {
            capcha.style.display = 'none';
            register_form.style.display = 'block';
            return false;
        }

        let n1 = Math.floor(Math.random() * 100);
        let n2 = Math.floor(Math.random() * 100);

        capcha_question.textContent = "Найдите сумму " + n1 + " + " + n2;
        answer = n1 + n2 + "";
        capcha_answer.value = "";
        attempts = 1;
        return false;
    }

    if(capcha_answer.value === answer) {
        capcha.style.display = 'none';
        register_form.style.display = 'block';
        return false;
    }

    capcha_question.textContent = "Вы робот!";
    capcha_answer.disabled = true;
    submit_capcha_button.disabled = true;
    submit_capcha_button.classList = null;

    return false;
}

class Ping extends HTMLElement {

    get uri() {
        return this.getAttribute('uri');
    }

    connectedCallback() {
        fetch(this.uri).then(r => r.json()).then(j => this.innerHTML = j[0].name);
    }
}

customElements.define('a-ping', Ping);

console.log('Hello World!');
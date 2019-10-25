const delay = 1000;

it('visits the app', () => {
    cy.visit('/');

    cy.wait(delay)
});

it('accepts input', () => {
    const input = "Learn about Cypress";
    cy.get('input')
        .clear()
        .type(input)
        .should('have.value', input);

    cy.wait(delay)
});


it('displays list of initial zero todo', () => {
    cy.get('li')
        .should('have.length', 0);

    cy.wait(delay)
});

it('adds a new todo', () => {
    const input = "Learn more about Cypress";

    cy.screenshot();

    cy.get('input')
        .clear()
        .type(input)
        .get('button')
        .click()
        .get('li')
        .should('have.length', 1)
        .screenshot();

    cy.wait(delay)
});

it('marks a todo as done', () => {
    cy.get('span')
        .first()
        .click()
        .get('li')
        .should('have.length', 1)
        .screenshot();

    cy.wait(delay)
});

it('deletes a todo', () => {
    cy.get('i')
        .first()
        .click()
        .get('li')
        .should('have.length', 0);

    cy.wait(delay)
});

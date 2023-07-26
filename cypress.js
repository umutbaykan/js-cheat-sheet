// Cypress

////// Commands
Cypress.Commands.add('login', (email, password) => {
  cy.visit("/login");
  cy.get("#email").type(email);
  cy.get("#password").type(password);
  cy.get("#submit").click();
})

Cypress.Commands.add('getByCy', (selector, ...args) =>
  cy.get(`[data-cy="${selector}"]`, ...args)
)

Cypress.Commands.add('getByCyLike', (selector, ...args) => {
  cy.get(`[data-cy*=${selector}]`, ...args)
})

////// Loading Fixtures
// Fixtures are auto searched in the folder called fixture, no need to import them inside the file
cy.fixture('jsonfile.json').as('alias').then((data) => {
  // do what you need to do with the data, for example:
  cy.intercept("POST", "/images", {imgUrl: data.imgUrl}).as("anotherAlias")
})

////// Basic syntax to get and assert
cy.mount(<ComponentGoesHere />)
cy.get('[data-cy="something"')
cy.get('#some-item') // <-- gets by id, not brilliant
cy.getByCy('something') // <-- custom fixture needs to be added
cy.getByCyLike('somethin') // <-- gets everything that starts with somethin
cy.get("@alias") // <-- gets the alias
cy.get('someQueryReturningMultiple').last() // <-- grabs the last one of the returned entries
cy.get().should('') // <-- like jQuery , has many alternatives
  // you can add not. in the beginning to assert for something that is not
  should('have.class', 'classname goes here')
  should('have.length', 5)
  should('have.text', 'text goes here')
  should('contain.text', 'text goes here')

  should('exist')
  should('not.exist')
  should('be.visible')
  should('not.be.visible')

  should('include', 'thing to include')

  invoke("attr", "src").should("contain", "add note here") // <-- can also access attributes through here
  should('have.attr', 'src').and('include', 'image.png') // <-- can chain .and to check the value of attribute

cy.get().type("hey I am typing")
cy.get().click()

////// Network commands
cy.visit('/urladdress') // <-- e2e only
cy.url().should("eq", Cypress.config().baseUrl + "/url-goes-here");
cy.url().should("include", "/login"); // <-- alternative to check for a substring

// intercepting GET requests
cy.intercept("GET", "/getTodos", {todos: ["do laundry", "go to the shops", "do something else"]}).as("noteGetRequest")
cy.get('@noteGetRequest.all').should('have.length', 0) // <-- check how many times it has been called

// intercepting POST requests
cy.intercept("POST", "/post", {response: "ok"}).as("notePostRequest") // <-- response is server response
cy.get('@notePostRequest.all').should('have.length', 0)
cy.wait('@notePostRequest').its('request.url').should('include', '/post') // <-- checks where the api call was made to
cy.get('@notePostRequest').should(({request, response}) => {
  expect(request.url).to.include('/post')
  expect(request.method).to.equal('POST')
  expect(request.body.note).to.contain(message)
})

// get useContext state in an isolated component -- Warning, this isnt great, probably a better way
cy.mount(
  <MemoryRouter initialEntries={["/"]}>
    <LobbyContext.Provider value={availableGames}>
      <Routes>
        <Route path="/" element={<CurrentGames />} />
      </Routes>
    </LobbyContext.Provider>
  </MemoryRouter>,
);

////// Stubs and spies
const createGameStub = cy.stub().as('createGameStub');
cy.mount(<GameConfigForm createGame={createGameStub}/>);
cy.get('@createGameStub').should('to.be.called');

// Cypress
// In TS, you need to add the reference below:
declare global {
  namespace Cypress {
    interface Chainable {
      mount: typeof mount;
      getByCy: typeof string;
      getByCyLike: typeof string;
    }
  }
}
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

// TS style adding
Cypress.Commands.add("successfulUnlikeResponse", (postId:number=1, userId:number=1279) => {
  cy.intercept(
    "DELETE",
    `https://chitter-backend-api-v2.herokuapp.com/peeps/${postId}/likes/${userId}`,
    {
      statusCode: 204,
    }
  ).as("successfulUnlikeResponse");
})

////// Loading Fixtures
// Fixtures are auto searched in the folder called fixture, no need to import them inside the file
cy.fixture('jsonfile.json').then((data) => {
  // do what you need to do with the data, for example:
  cy.intercept("POST", "/images", {imgUrl: data.imgUrl}).as("anotherAlias")
})

// if you want to import in a test suite
let someTestPreCondition

beforeEach(() => {
  cy.fixture("fullRows").then((data) => (someTestPreCondition = data));
  cy.intercept("GET", "/getinfo", { data: someTestPreCondition }).as("listGetRequest");
});

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
  should("have.value", "Hello world!") // <-- to check the value of existing items
  should('contain.text', 'text goes here')

  should('exist')
  should('not.exist')
  should('be.visible')
  should('not.be.visible')
  // if you want to check children
  should('exist').within(() => { 
    cy.get('h3').should('not.be.empty');
    cy.get('p').should('not.be.empty');
  })

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
// to check the request body and methods
cy.get('@notePostRequest').should(({request, response}) => {
  expect(request.url).to.include('/post')
  expect(request.method).to.equal('POST')
  expect(request.body.note).to.contain(message)
})

// get useContext state in an isolated component 
cy.mount(
  <MemoryRouter initialEntries={["/"]}>
    <LobbyContext.Provider value={availableGames}>
      <Routes>
        <Route path="/" element={<CurrentGames />} />
      </Routes>
    </LobbyContext.Provider>
  </MemoryRouter>,
);

// cookies are async operations and slightly weird on cypress, especially getCookie
cy.wait(500);
cy.getCookie("sessionKey").should(
  "have.property",
  "value",
  "_2a_12_fCA0TZISF2XZ5e17E5cgOe",
);
// Assertion above is the same as
cy.getCookie("sessionKey").should((cookie) => {
  expect(cookie.value).to.equal("_2a_12_fCA0TZISF2XZ5e17E5cgOe");
});
// Also you can use cy.wrap if you dont want to use chai methods
cy.wrap(cookie.value).should("equal", "_2a_12_fCA0TZISF2XZ5e17E5cgOe")


// get to check useNavigate in a component based context
// The routes that are redirected to are just plain html texts. You can set your asserts to check if your component manages to reach that page
cy.mount(
  <MemoryRouter initialEntries={["/navbar"]}>
    <Routes>
      <Route path={"/navbar"} element={<NavBar />} />
      <Route path={"/"} element={<h1>Home</h1>} />
      <Route path={"/login"} element={<h1>Login</h1>} />
      <Route path={"/signup"} element={<h1>Sign Up</h1>} />
    </Routes>
  </MemoryRouter>,
);

it("when clicked, the home button should take you to home route", () => {
  cy.getByCy("link-home").click();
  cy.get("h1").should("have.text", "Home");
});


////// Stubs and spies
const createGameStub = cy.stub().as('createGameStub');
cy.mount(<GameConfigForm createGame={createGameStub}/>);
cy.get('@createGameStub').should('to.be.called');

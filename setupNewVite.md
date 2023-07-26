# Create the app skeleton
npm create vite@latest
cd <yourprojectname>
npm install

# Add cypress and plugins to eslint
npm install cypress --save-dev
npm install eslint-plugin-cypress@latest --save-dev

Add these in your eslint file ->
  extends : ["plugin:cypress/recommended"]
  plugins : ["cypress"]

Add these in your scripts ->
  scripts : {
    "test:open": "cypress open",
    "test:single": "cypress run --component --spec",
    "test:unit": "cypress run --component",
    "test:feature": "cypress run"
  }

Add these in your .gitignore ->
cypress/screenshots
cypress/videos

# Create a .eslintignore file and add this line below
cypress.config.js

# Launch cypress and create both component and e2e configs
npm run test open

Add this in your cypress.config.js so you point at the right server ->
e2e: {baseUrl: 'http://localhost:5173'}

# Add these selectors to cypress commands.js
Cypress.Commands.add('getByCy', (selector, ...args) =>
  cy.get(`[data-cy="${selector}"]`, ...args)
)

Cypress.Commands.add('getByCyLike', (selector, ...args) => {
  cy.get(`[data-cy*=${selector}]`, ...args)
})

# Create the components folder
mkdir components

# Install prettier
npm install --save-dev --save-exact prettier

# Add this line in your scripts
scripts : {
  "pretty": "npx prettier --write cypress/e2e/**/* src/*"
}

# Clean up all the clutter from starter code
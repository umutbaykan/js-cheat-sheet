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
    
    // or
    "test": "cypress open",
    "test:component": "cypress run --config video=false --component",
    "test:single": "cypress run --config video=false --component --spec",
    "test:e2e": "cypress run --config video=false --e2e",
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

(If typescript, add these into your cypress.d.ts file:
import { mount } from 'cypress/react';

// Augment the Cypress namespace to include type definitions for
// your custom command.
// Alternatively, can be defined in cypress/support/component.d.ts
// with a <reference path="./component" /> at the top of your spec.
declare global {
  namespace Cypress {
    interface Chainable {
      mount: typeof mount;
      getByCy: typeof string;
      getByCyLike: typeof string;
    }
  }
}
)

# Create the components folder
mkdir components

# Install prettier
npm install --save-dev --save-exact prettier

# Add this line in your scripts
scripts : {
  "pretty": "npx prettier --write cypress/e2e/**/* src/*"
}

# Clean up all the clutter from starter code
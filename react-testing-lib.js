// React Testing Library

///// Selectors
const pElement = screen.getByTestId("unique-id");
const linkElement = screen.getByText(/learn react/i);
const elements = screen.getAllByTestId('something') // Example: Selecting all elements with data-testid="something"

///// Simulators -- Be careful, these are considered asynchronous and needs to be handled accordingly
fireEvent.click(buttonElement);

///// Assertions
expect(pElement).toBeInTheDocument(); // equivalent to should exist
expect(screen.queryByTestId('abc')).not.toBeInTheDocument()
expect(pElement).toHaveLength(3); // equivalent to length of array
expect(pElement).toHaveClass('classname goes here')
expect(screen.getByTestId('abc')).toHaveTextContent('text goes here')
expect(screen.getByTestId('abc')).toHaveValue('Hello world!')
expect(screen.getByTestId('abc')).toBeVisible()
expect(abcElement).toHaveAttribute('src'); // attribute check
expect(abcElement.getAttribute('src')).toContain('image.png'); // attribute read

const h3Element = within(pElement).getByText('h3 text goes here'); // assertions ran on children of html element
expect(h3Element).not.toBeEmptyDOMElement();

// Async assertions
await waitFor(() => {
  const pElement = screen.getByTestId("unique-id");
  expect(pElement).toHaveTextContent("server response")  
});

await waitFor(() => {
  expect(resolver).toHaveBeenCalledTimes(1)
  expect(resolver).toHaveBeenCalledWith("contents");
});

// You can also use expect to make assertions about the request
expect(server.requests[0].method).toBe('POST');
expect(server.requests[0].url).toBe('/api/data');
expect(JSON.parse(server.requests[0].body)).toEqual({ key: 'value' });

///// Network requests - npm install msw --save-dev
// First need to setup the mock server
import { rest } from 'msw';
import { setupServer } from 'msw/node';

const resolver = jest.fn()

const server = setupServer(
  rest.get('/getTodos', (req, res, ctx) => {
    return res(
      ctx.json({
        todos: ["do laundry", "go to the shops", "do something else"],
      })
    );
  }),

  rest.post('/postTodos', (req, res, ctx) => {
    const { login } = req.json()

    // Can do conditional returns to api calls
    if (login !== 'test@example.com') {
      return res(ctx.status(401), ctx.json({ success: false }))
    } else {
      return res(
        ctx.json({
          response: ["server response"]
        })
      )
    }    
  }),

  rest.post('/cart', resolver) // to run assertions on the request, export the resolver to the test to check
  
  // Multiple responses to same route
  rest.get('/api/data', (req, res, ctx) => {
    requestCounter++;

    if (requestCounter === 1) {
      return res(ctx.json({ message: 'First Response' }));
    } else if (requestCounter === 2) {
      return res(ctx.json({ message: 'Second Response' }));
    } else {
      // You can handle subsequent requests differently, return an error, or any other logic
      return res(ctx.status(404), ctx.json({ message: 'Not Found' }));
    }
  })
);

export { server };
// Once setup, need to call the mock server inside the test file
// Start the mock server before the tests
beforeAll(() => server.listen());

// Reset handlers after each test
afterEach(() => server.resetHandlers());

// Clean up and stop the mock server after the tests
afterAll(() => server.close());


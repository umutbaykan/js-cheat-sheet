// Some JS bits

////// Fetch

const imageClientCall = (userChoices) => {
  fetch("/images", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userChoices),
  })
  .then((response) => {
    if (!response.ok) {
      const error = new Error(`HTTP Error: Status ${response.status}`);
      error.status = response.status;
      throw error;
    }
    return response.json();
  })
    .then((data) => {
      setImgUrl(data.imgUrl);
      updateStorageAndHooks("imageHistory", data["imgUrl"]);
      setSDLoaded(true);
    });
};

////// Iterations
for (let i = 0; i < 10; i++) {
  // do something with your loop
}

while (i<10) {
  // do something
}

// dont forget to return the element if you open brackets
{rows.map((row) => {
  return <Row key={row.id} data={row} />;
})}

// alternative
{rows.map((row) => 
  <Row key={row.id} data={row} />
)}

////// Objects

// Access the key value pairs of an object
Object.entries(object).map(([key, value]) => {
  // do something with key
  // do something with value
 })

// Access the keys of an object
Object.keys(object).map((key) => {
  // do something with key
})

for (property in object) {
  // do something with key
}

// Access the values of an object
Object.values(object).reduce(
  (sum, value) => sum + value, 0
)

/////// Arrays

// Creation
Array.from({length: 5}, () => {return "hello"}) // -> [ 'hello', 'hello', 'hello', 'hello', 'hello' ]

// Grid creation with cell components
{Array.from({ length: gameState.boards[index].size }, (_, rowIndex) => (
  <div className="row" key={rowIndex}>
    {Array.from(
      { length: gameState.boards[index].size },
      (_, colIndex) => (
        <Cell
          key={colIndex}
          coordinates={[rowIndex, colIndex]}
          display={
            (parsedBoard[JSON.stringify([rowIndex, colIndex])] || {})
              .symbol
          }
          type={
            (parsedBoard[JSON.stringify([rowIndex, colIndex])] || {})
              .class
          }
          action={action}
        />
      )
    )}
  </div>
))}

// Iterate over - does not modify elements at all
arr.forEach((element) => {
  return element *= 2
})

arr.forEach((element, index) => {
  // do something
  // all iterations of arr can take an index in as a second parameter
})

// Mapping
arr.map((element) => {
  return element *= 2
}) // <-- change the element within, does not modify original array

// Filtering
arr.filter((element) => {
  if (element === "something") {return true}
  // this does not modify the original array
})

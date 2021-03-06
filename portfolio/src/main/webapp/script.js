// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/**
 * Async function loads the random fact server content
 */
async function randomFactLoadFromServer(){
    
    const serverResponse = await fetch('/facts')
        .catch((error) => {
            // error is here!
            console.log("[ERROR]"+error)
          });
    const serverResponseJson = await serverResponse.json();
    console.log(serverResponseJson);
    // Pick a random fact.
    const fact = serverResponseJson[Math.floor(Math.random() * serverResponseJson.length)];

    // Add it to the page.
    const factContainer = document.getElementById('fact-container');
    factContainer.innerText = fact['fact'];

}

/**
 * Async function loads the contact me data from the DataStore
 */
async function loadContactFormData(){
    const serverResponse = await fetch('/load-data');
    const serverResponseJson = await serverResponse.json();
    const formDataContainer = document.getElementById('form-data-display-container');

    // use JSON library to print the string of the response
    formDataContainer.innerText = JSON.stringify(serverResponseJson);
    console.log(serverResponseJson);
}
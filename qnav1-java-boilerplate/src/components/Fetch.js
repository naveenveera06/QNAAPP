

const API = "http://localhost:8080";
const DEFAULT_QUERY = "/getTopics";

export function getTopics() {
  fetch(API + DEFAULT_QUERY)
    .then(response => {
      return response.json();
    }).then(result => {
      this.setState({
        topics: result
      });
      console.log(this.state.topics);
    })
    .catch(error => console.log(error));
}

// export default { getTopics };
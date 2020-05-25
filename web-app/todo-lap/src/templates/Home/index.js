import React from "react";

import Image from "../../components/image";
import { Link } from "gatsby";

class Home extends React.PureComponent {
  render() {
    return (
      <main>
        <h1>Hi people</h1>
        <p>Welcome to your new Gatsby site.</p>
        <p>Now go build something great.</p>
        <div style={{ maxWidth: `300px`, marginBottom: `1.45rem` }}>
          <Image />
        </div>
        <Link to="/page-2/">Go to page 2</Link>
      </main>
    );
  }
}

export default Home;

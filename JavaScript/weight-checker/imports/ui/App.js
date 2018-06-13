import React, { Component } from 'react';
import Chart from '../components/Chart';
import ReactDOM from 'react-dom';
import { withTracker } from 'meteor/react-meteor-data';
import moment from 'moment';

import { WeightData } from '../api/data.js';

// App component - represents the whole app
//export default class App extends Component {
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
        weightData: {
          labels: this.getDates(),
          datasets:[{
            label:'Weight',
            data: this.getWeightData(),
            backgroundColor:'green'
          }]
        },
        waistData: {
          labels: this.getDates(),
          datasets:[{
            label:'Month',
            data:this.getWaistData(),
            backgroundColor:'blue'
          }]
        },
        currentDate: this.getTodayDate(),
        latestWeight: 198,
        latestWaist: 39
    }
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit=this.handleSubmit.bind(this);
  }

  getTodayDate() {
    const rightNow = new Date();
    return new Date(rightNow.getFullYear(),
                                rightNow.getMonth(),
                                rightNow.getDate())
  }

  shouldComponentUpdate(nextProps, nextState) {
    return true;
  }

  getDates() {
    return this.props.myWeightData.map(
      (dailyData) => dailyData.date);
  }

  getWeightData() {
    return this.props.myWeightData.map((dailyData) => {
      return { x: dailyData.date, y: dailyData.weight};
    }
    );
  }

  getWaistData() {
    return this.props.myWeightData.map((dailyData) => {
      return { x: dailyData.date, y: dailyData.waist};
    }
  );
  }

  handleInputChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;

    this.setState({
      [name]: value
    });
  }

  handleSubmit(event) {
    event.preventDefault();

    // find current inputs
    const dateAsString = ReactDOM.findDOMNode(this.refs.dateRef).value.trim();
    const latestWeight = ReactDOM.findDOMNode(this.refs.weightRef).value.trim();
    const latestWaist = ReactDOM.findDOMNode(this.refs.waistRef).value.trim();

    // Do not execute the rest of the function if no date was entered
    if (dateAsString === "") {
      return;
    }

    // Convert date to a JavaScript Date type
    const UTCMs = Date.parse(dateAsString);
    const currentDateUTC = new Date();
    currentDateUTC.setTime(UTCMs);
    const currentDate = new Date(currentDateUTC.getUTCFullYear(),
                                currentDateUTC.getUTCMonth(),
                                currentDateUTC.getUTCDate());
    // Allow user to update previously bad inputted points
    const potentialConflict = WeightData.findOne({date: currentDate});

    if (potentialConflict) {
      WeightData.remove({_id: potentialConflict._id});
    }

    WeightData.insert({
      date: currentDate,
      weight: latestWeight,
      waist: latestWaist
    });
  }

  render() {
    const rightNow = new Date();
    const currentDate = new Date(rightNow.getFullYear(),
                                rightNow.getMonth(),
                                rightNow.getDate())
    localweightdata = {
      labels: this.getDates(),
      datasets:[{
        label:'Weight',
        data: this.getWeightData(),
        backgroundColor:'green'
      }]
    }
    localwaistdata = {
      labels: this.getDates(),
      datasets:[{
        label:'Waist',
        data: this.getWaistData(),
        backgroundColor:'blue'
      }]
    }
    return (
      <div className="container">
        <header>
          <h1>My weight follow up website</h1>
          <form className="New Day" onSubmit={this.handleSubmit}>
            <label>
              Enter the date for your data:
            </label>
            <input
              name="currentDate"
              type="date"
              ref="dateRef"
              onChange={this.handleInputChange} />
            <br />
            <label>
              Enter your weight for today (lbs):
            </label>
            <input
              name="latestWeight"
              type="number"
              ref="weightRef"
              value={this.state.latestWeight}
              onChange={this.handleInputChange} />
            <br />
            <label>
              Enter your waist for today (in):
            </label>
            <input
              name="latestWaist"
              type="number"
              ref="waistRef"
              value={this.state.latestWaist}
              onChange={this.handleInputChange} />
            <br />
            <label>
              Submit new point:
            </label>
            <input type="submit" value="Submit" />
          </form>
        </header>

        <Chart weightData={localweightdata}
        waistData={localwaistdata}/>

      </div>
    );
  }
}

export default withTracker(() => {
  console.log(WeightData.find({}).fetch());
  return {
    myWeightData: WeightData.find({}, { sort: { date: -1 }, limit: 20 }).fetch()
  };
})(App);

import React, { Component } from 'react';
import {Line} from 'react-chartjs-2';
import { withTracker } from 'meteor/react-meteor-data';

import { WeightData } from '../api/data.js';

class Chart extends Component {
  constructor(props) {
    super(props);
    this.state = {
      weightData: props.weightData,
      waistData: props.waistData
    }
  }

  /*shouldComponentUpdate(nextProps, nextState) {
    console.log('Chart Should Update called')
    this.state = {
      weightData: nextProps.weightData,
      waistData: nextProps.waistData
    }
    return true;
  }*/

  static defaultProps = {
    displayTitle:true,
    displayLegend:true,
    legendPosition:'right',
    timeScale: {
      xAxes: [{
        type: 'time',
        time: {
          unit: 'day',
          unitStepSize: 1,
          displayFormats: {
            'day': 'MMM DD'
          }
        },
        scaleLabel: {
          display: true,
          labelString: 'Date'
        }
      }]
    }
  }

  render() {
    return (
      <div className="chart">

        <Line
          data={this.props.weightData}
          options={{
            title:{
              display:this.props.displayTitle,
              text:'My weight',
              fontSize:25
            },
            legend:{
              display:this.props.displayLegend,
              position:this.props.legendPosition
            },
            scales: this.props.timeScale,
            tooltips: {
              enabled: true,
              mode: 'single',
              callbacks: {
                label: function(tooltipItems, data) {
                  let multistring = [tooltipItems.yLabel + " (lbs)"];
                  multistring.push(Math.round(tooltipItems.yLabel/75.0/75.0*703.0*10)/10 + " (BMI)");
                  return multistring;
                }
              }
            }
          }}
        />

        <Line
          data={this.props.waistData}
          options={{
            title:{
              display:this.props.displayTitle,
              text:'My waist',
              fontSize:25
            },
            legend:{
              display:this.props.displayLegend,
              position:this.props.legendPosition
            },
            scales: this.props.timeScale
          }}
        />

      </div>
    );
  }
}

export default Chart;

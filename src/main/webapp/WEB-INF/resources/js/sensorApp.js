/**
 * Created by marco on 06/06/16.
 */
import _ from 'lodash';
import React, { Component } from 'react';
import ReactDOM from 'react-dom';

class SensorApp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sensors: [],
            selectedSensor: null
        };

    }


    render() {
        // not more frequently than every 300ms
        //const sensorSearch = _.debounce((term) => { this.sensorSearch(term)}, 300);
        return (
            <div>
                <SearchBar onSearchTermChange={videoSearch}/>
                <VideoDetail video={this.state.selectedVideo} />
                <VideoList
                    onVideoSelect={selectedVideo => this.setState({selectedVideo})}
                    videos={this.state.videos}/>
            </div>
        );
    }
}

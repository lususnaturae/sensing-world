/**
 * Created by marco on 24/05/16.
 */

var SensorListRow = React.createClass({
    componentDidMount: function () {
        // console.log(this.props.url);
        $.ajax({
            url: "http://localhost:8888/api/sensors/list",
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.setState({data: data});
                console.log(data);
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },
    render: function () {
        console.log("Täällä");
        var sensors = this.props.data.map(function (sensor) {
            return (
                <tr>
                    <td>
                        {this.props.name}
                    </td>
                    <td>
                        {this.props.usagetoken}

                    </td>
                </tr>
            );
        });
    }

});
var SensorListTable = React.createClass({
    getInitialState: function () {
        return {};
    },

    render: function () {

        return (
            <table className="table table-hover">
                <thead>
                <tr>
                    <th >Nimi</th>
                    <th >Tyyppi</th>
                </tr>
                </thead>
                <tbody>
                <tr>

                </tr>
                </tbody>
            </table>
        );
    }
});

var SensorListContainer = React.createClass({
    render: function () {
        return (

            <div className="panel panel-default">
                <div className="panel-heading">
                    <h3 className="panel-title">
                        SENSOR CRUD
                    </h3>
                </div>
                <div className="panel-body">
                    <div className="col-md-6">
                        <SensorListTable />
                    </div>
                    <div className="col-md-6">

                    </div>
                </div>
            </div>



        );
    }
});
ReactDOM.render(
    <SensorListContainer  />,
    document.getElementById('sensorcrud')
);
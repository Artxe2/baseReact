import React from 'react'

class CustomerDELETE extends React.Component {
    
    deleteCustomer(id) {
        const url = '/api/customers/' + id;
        fetch(url, {
            method: 'DELETE'
        }).then(res => {
            console.log(res);
            this.props.stateRefresh();
        });
    }

    render() {
        return (
            <button onClick={(e) => {this.deleteCustomer(this.props.id)}}>삭제</button>
        )
    }
}

export default CustomerDELETE;
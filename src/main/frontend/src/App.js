import React, { Component } from 'react';
import Customers from './components/Customers'
import CustomerPost from './components/CustomersPost'
import PrimarySearchAppBar from './components/PrimarySearchAppBar'
import './App.css';
import Table from '@material-ui/core/Table'
import Paper from '@material-ui/core/Paper'
import TableHead from '@material-ui/core/TableHead'
import TableBody from '@material-ui/core/TableBody'
import TableRow from '@material-ui/core/TableRow'
import TableCell from '@material-ui/core/TableCell'
import CircularProgress from '@material-ui/core/CircularProgress'
import { withStyles } from '@material-ui/core/styles'


/*

1) cunstructor()

2) componentWillMount()

3) render()

4) componentDidMount()

*/

/*

props or state => shouldComponentUpdate()

*/

const styles = theme => ({
  // root: {
  //   width: '100%',
  //   marginTop: theme.spacing(3),
  //   overflowX: 'auto'
  // },
  paper: {
    marginLeft: 18,
    marginRight: 18
  },
  table: {
    minWidth: 1080
  },
  menu: {
    marginTop: 15,
    marginBottom: 15,
    display: 'flex',
    justifyContent: 'center'
  },
  tableHead: {
    fontSize: '1.2rem'
  },
  progress: {
    margin: theme.spacing(2)
  }
})

class App extends Component {
  constructor(props) { 
    super(props);
    this.state = {
      customers: '',
      completed: 0,
      searchKeyword: ''
    }
  }

  stateRefresh = () => {
    this.setState({
      customers: '',
      completed: 0,
      searchKeyword: ''
    });
    this.callApi()
      .then(res => this.setState({customers: res}))
      .catch(err => console.log(err));
  }

  componentDidMount() {
    this.timer = setInterval(this.progress, 20);
    this.callApi()
      .then(res => this.setState({customers: res}))
      .catch(err => console.log(err));
  }

  callApi = async() => {
    const response = await fetch('/api/customers');
    const body = await response.json();
    return body;
  }

  progress = () => {
    const { completed } = this.state;
    this.setState({ completed: completed >= 100 ? 0 : completed + 1});
  }

  handleSearchKeyword = (value) => {
    const nextState = {
      searchKeyword: value
    }
    this.setState(nextState);
    console.log('searchKeyword: ' + this.state.searchKeyword);
  }

  filteredComponents = () => {
    const data = this.state.customers.filter(c => c.NAME.indexOf(this.state.searchKeyword) > -1);
    return (
      data.map(c => {
        return (
          <Customers 
            key={c.ID}
            id={c.ID}
            image={c.IMAGE}
            name={c.NAME} 
            birthday={c.BIRTHDAY} 
            gender={c.GENDER} 
            job={c.JOB}
            stateRefresh={this.stateRefresh}
          />
        )
      })
    )
  }

  render() {
    const { classes } = this.props;
    const cellList = ['번호', '이미지', '이름', '생년월일', '성별', '직업', '설정'];
    return (
      <div>
        <PrimarySearchAppBar handleSearchKeyword={this.handleSearchKeyword} searchKeyword={this.state.searchKeyword}/>
        <div className={classes.menu}>
          <CustomerPost stateRefresh={this.stateRefresh}/>
        </div>
        <Paper className={classes.paper}>
          <Table className={classes.table}>
            <TableHead>
              <TableRow>
                {cellList.map(c => {
                  return (
                    <TableCell key={c} className={classes.tableHead}>{c}</TableCell>
                  )
                })}
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.customers ? 
                this.filteredComponents() : 
                <TableRow>
                  <TableCell colSpan='6' align='center'>
                    <CircularProgress className={classes.progress} variant='indeterminate' value={this.state.completed}/>
                  </TableCell>
                </TableRow>
              }
            </TableBody>
          </Table>
        </Paper>
      </div>
    )
  }
}

export default withStyles(styles)(App);
import { useEffect, useState } from 'react';
import userService from '../../services/user.service';

const UserList = () => {
    const[user, setUser] = useState('');
    const[id,setId] = useState(1);
    console.log(user)
    const init = () => {
        console.log(id);
        userService.get(id)
        .then(response => {
            console.log('Printing employees data', response.data);
            setUser(response.data);
            console.log(user)
          })
          .catch(error => {
            console.log('Something went wrong', error);
          }) 
    }
    useEffect(() => {
        init();
      },);

  return (
    <div>
        <h3>enter id : </h3>
        <input id='id' type='number' onChange={(e) => setId(e.target.value)}/>
        <button onClick={() => {init()}}>Get User</button>
        <h3>{user.firstName}</h3>
        <h3>{user}</h3>
        
    </div>
  )
}

export default UserList
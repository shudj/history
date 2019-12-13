import axios from 'axios'
import { Message } from 'element-ui'
const service = axios.create({
  baseURL: '/api',
  headers: { 'content-type': 'application/x-www-form-urlencoded' },
  timeout: 5000
})

service.interceptors.response.use(
  response => response,
  error => {
    console.log(error)
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service

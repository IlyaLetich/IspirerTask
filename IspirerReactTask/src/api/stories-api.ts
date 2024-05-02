import axios from "axios"

const instance = axios.create({
    baseURL: 'https://hacker-news.firebaseio.com'
});

export const storiesApi = {
    getStories() {
        return instance.get('/v0/newstories.json')
            .then((response) => {
                return response.data;
            })
    },
    getStoriesData(id: number) {
        return instance.get(`/v0/item/${id}.json`)
            .then((response) => {
                return response.data;
            })
    }
}
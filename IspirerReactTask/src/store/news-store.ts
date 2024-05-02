import { makeAutoObservable } from "mobx";
import { storiesApi } from "../api/stories-api";

export type StoryInfo = {
    by?: string,
    descendants?: number,
    id?: number,
    kids?: number[],
    score?: number,
    time?: number,
    title?: string
    type?: string
    url?: string
}

export const defaultStateNews = {
    by: '',
    descendants: 0,
    id: 0,
    kids: [],
    score: 0,
    time: 0,
    title: '',
    type: '',
    url: '',
}

export class NewsStore {
    news: StoryInfo = defaultStateNews;

    constructor() {
        makeAutoObservable(this);
    }

    setNews(value: StoryInfo) {
        this.news = value;
    }

    async fetchStories(id: number, onError?: () => void): Promise<void> {
        try {
            const result = await storiesApi.getStoriesData(id);
            if(result === null && onError !== undefined) onError();
            this.setNews(result);
        } catch (err) {
            if (onError !== undefined) onError();
        }
    }
}

export const newsStore = new NewsStore();
